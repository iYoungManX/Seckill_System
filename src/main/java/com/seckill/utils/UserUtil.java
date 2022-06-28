package com.seckill.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seckill.pojo.User;
import com.seckill.vo.RespBean;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// generate many different customer
public class UserUtil {
    public static void main(String[] args) throws Exception {
        creatUser(5000);
    }


    public static void creatUser(int count) throws Exception {
        List<User> users= new ArrayList<>(count);
        for (int i=0; i<count;i++){
            User user = new User();
            user.setId(13000000000L+i);
            user.setNickname("user"+i);
            user.setSalt("1a2b3c4d");
            user.setLoginCount(1);
            user.setPassword(MD5Util.inputPassToDBPass("123456",user.getSalt()));
            user.setRegisterDate(new Date());
            users.add(user);
        }
        System.out.println("User created!!!!");
//         Connection conn = getConn();
//         String sql = "insert into t_user(login_count, nickname, register_date, salt, password, id)values(?,?,?,?,?,?)";
//         PreparedStatement pstmt = conn.prepareStatement(sql);
//         for (int i = 0; i < users.size(); i++) {
//         	User user = users.get(i);
//         	pstmt.setInt(1, user.getLoginCount());
//         	pstmt.setString(2, user.getNickname());
//         	pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
//         	pstmt.setString(4, user.getSalt());
//         	pstmt.setString(5, user.getPassword());
//         	pstmt.setLong(6, user.getId());
//         	pstmt.addBatch();
//         }
//         pstmt.executeBatch();
//         pstmt.close();
//         conn.close();
//        System.out.println("insert into DB");
        // 登录
        String urlString="http://localhost:8080/login/doLogin";
        File file = new File("/Users/yaozonghui/Desktop/Java/seckill/src/main/resources/config.txt");
        if(file.exists()){
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection) url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile=" + user.getId() + "&password=" + MD5Util.inputPassToFromPass("123456");
            System.out.println(params);
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte buff[] = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buff)) >= 0) {
                bout.write(buff, 0, len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            ObjectMapper mapper = new ObjectMapper();
            //System.out.println(response);
//            JSONObject jo = JSON.parseObject(response);
//            String token = jo.getString("data");
            // data为edu.uestc.controller.result.Result中的字段
            RespBean respBean = mapper.readValue(response, RespBean.class);
            String userTicket = ((String) respBean.getObj());
            System.out.println("create userTicket : " + user.getId());

            String row = user.getId() + "," + userTicket;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();

        System.out.println("over");
    }

    private static Connection getConn() throws Exception {
        String url="jdbc:mysql://localhost:3306/seckill?useUnicode=true&characterEncoding=UTF-8&&serverTimezone=UTC";
        String username ="root";
        String password ="root";
        String driver= "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(url,username,password);
    }
}
