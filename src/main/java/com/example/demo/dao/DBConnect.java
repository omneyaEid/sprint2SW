package com.example.demo.dao;
import com.example.demo.model.NotificationTemplat;
import com.example.demo.model.language;
import com.example.demo.model.language;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBConnect {
    public static List<NotificationTemplat> EMAIL=new ArrayList<>();
    public static List<NotificationTemplat> SmS=new ArrayList<>();
    public static List<NotificationTemplat> EmailAndSms=new ArrayList<>();
    void add(NotificationTemplat notification) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/tom";
            String user = "root";
            String password = "12345";
            Connection Con = null;
            Statement Stat = null;
            Con = DriverManager.getConnection(url, user, password);
            Stat = Con.createStatement();
            System.out.println("yes");
            if(notification.getType().equals("Email")) {
                String line = "INSERT INTO Email(id,content,type,language,subject) VALUES("
                        + "'" + notification.getId() + "',"
                        + "'" + notification.getContent() + "',"
                        + "'" + notification.getType() + "',"
                        + "'" + notification.getLanguage() + "',"
                        + "'" + notification.getSubject() + "')";

                Stat.executeUpdate(line);
            }
            else
            {
                String line = "INSERT INTO SMS(id,content,type,language,subject) VALUES("
                        + "'" + notification.getId() + "',"
                        + "'" + notification.getContent() + "',"
                        + "'" + notification.getType() + "',"
                        + "'" + notification.getLanguage() + "',"
                        + "'" + notification.getSubject() + "')";

                Stat.executeUpdate(line);
            }
            Stat.close();
            Con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("no");
        }
    }

    List<NotificationTemplat>selectAllEmail() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/tom";
            String user = "root";
            String password = "12345";
            Connection Con = null;
            Statement Stat = null;
            Con = DriverManager.getConnection(url, user, password);
            Stat = Con.createStatement();

            String email= "Select * from Email ";
            ResultSet rs = Stat.executeQuery(email);

            while ( rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                String type = rs.getString("type");
                String langu = rs.getString("language");
                String subject = rs.getString("subject");

                String p = id + " " + content + " " + type + " " + langu + " " + subject + " " ;
                //Enum to String using Enum.valueOf()
                Enum lang2 = Enum.valueOf(language.class, langu);

                //Enum to String using Currency.valueOf()
                lang2 = language.valueOf(langu);

                EMAIL.add(new NotificationTemplat(id,content
                        ,type, (language) lang2,subject));


                EmailAndSms.add(new NotificationTemplat(id,content
                        ,type, (language) lang2,subject));

            }

            Stat.close();
            Con.close();
            return EmailAndSms;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("no");
        }
        return null;
    }
    List<NotificationTemplat>selectAllSMS()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/tom";
        String user = "root";
        String password = "12345";
        Connection Con = null;
        Statement Stat = null;
        Con = DriverManager.getConnection(url, user, password);
        Stat = Con.createStatement();
        String sms = "Select * from SMS";
        ResultSet rs1 = Stat.executeQuery(sms);

        while ( rs1.next()) {
            int id = rs1.getInt("id");
            String content = rs1.getString("content");
            String type = rs1.getString("type");
            String language2 = rs1.getString("language");
            String subject = rs1.getString("subject");

            String p2 = id + " " + content + " " + type + " " + language2 + " " + subject + " " ;
            Enum langsms = Enum.valueOf(language.class, language2);

            //Enum to String using Currency.valueOf()
            langsms = language.valueOf(language2);

            SmS.add(new NotificationTemplat(id,content
                    ,type, (language) langsms,subject));


            EmailAndSms.add(new NotificationTemplat(id,content
                    ,type, (language) langsms,subject));

        }

            Stat.close();
            Con.close();
            return EmailAndSms;


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("no");
        }
        return null;
    }
    List<NotificationTemplat> selectAll()
    {
        selectAllSMS();
        selectAllEmail();
        return EmailAndSms;
    }
    Optional<NotificationTemplat> selectNotificationById(int id)
    {
        return EmailAndSms.stream().filter(notification -> notification.getId()==(id)).findFirst();
    }
    public void DeleteFromDataBase(String type,int ids)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/tom";
            String user = "root";
            String password = "12345";
            Connection Con = null;
            Statement Stat = null;
            Con = DriverManager.getConnection(url, user, password);
            Stat = Con.createStatement();
            System.out.println("uuu");
            System.out.println(type);
            if(type.equals("SMS")) {

                String sql1 = "DELETE FROM SMS WHERE id ="+ids;
                Stat.execute(sql1);
            }
            else if(type.equals("Email"))
            {
                 String sql = "DELETE FROM Email WHERE id =" +ids ;
                   Stat.execute(sql);
            }
            System.out.println("hello");
            Stat.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("nooooo");
        }
    }
    public int  Delete(int id)
    {
        Optional<NotificationTemplat>notificationMaybe=selectNotificationById(id);
        if(notificationMaybe.isEmpty())
        {
            return 0;
        }
        if(notificationMaybe.get().getType().equals("SMS"))
        {
            DeleteFromDataBase("SMS",id);
        }
        else if(notificationMaybe.get().getType().equals("Email"))
        {
            DeleteFromDataBase("Email",id);
        }
       EmailAndSms.remove(notificationMaybe.get());
        return 1;
    }
    public void UpdateToDataBase(int id,NotificationTemplat update)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/tom";
            String user = "root";
            String password = "12345";
            Connection Con = null;
            Statement Stat = null;
            Con = DriverManager.getConnection(url, user, password);
            Stat = Con.createStatement();
            PreparedStatement pstatement;
            //System.out.println("uuu");
            if(update.getType().equals("SMS")) {

                String sql = "UPDATE SMS set id=?,content=?,type=?,language=?,subject=?  where id="+id;
                PreparedStatement preparedStmt = Con.prepareStatement(sql);
                preparedStmt.setInt   (1, update.getId());
                preparedStmt.setString(2, update.getContent());
                preparedStmt.setString(3, update.getType());

                preparedStmt.setString(4, update.getLanguage().toString());
                preparedStmt.setString(5, update.getSubject());
                preparedStmt.executeUpdate();

            }
            else if(update.getType().equals("Email"))
            {
                String sql = "UPDATE Email set id=?,content=?,type=?,language=?,subject=?  where id="+id;
                PreparedStatement preparedStmt = Con.prepareStatement(sql);
                preparedStmt.setInt   (1, update.getId());
                preparedStmt.setString(2, update.getContent());
                preparedStmt.setString(3, update.getType());

                preparedStmt.setString(4, update.getLanguage().toString());
                preparedStmt.setString(5, update.getSubject());
                preparedStmt.executeUpdate();
            }
            System.out.println("hello");

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("nooooo");
        }
    }
    public int update(int id,NotificationTemplat update)
    {
        if(update.getType().equals("SMS"))
        {
            UpdateToDataBase( id, update);
        }
        else if(update.getType().equals("Email"))
        {
            UpdateToDataBase( id, update);
        }
        return 1;
    }
    public void UpdateToDataBaseWithPlaceHolder(int id,NotificationTemplat update)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/tom";
            String user = "root";
            String password = "12345";
            Connection Con = null;
            Statement Stat = null;
            Con = DriverManager.getConnection(url, user, password);
            Stat = Con.createStatement();
            PreparedStatement pstatement;
                // type == name , subject == book

                String NewContent = update.getContent().replace("{x}", update.getType());
                NewContent = NewContent.replace("{y}", update.getSubject());
                String sql = "INSERT INTO PlaceHolder(id,content)VALUES("
                        + "'" + id + "',"
                        + "'" +NewContent+ "')";


            Stat.executeUpdate(sql);
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("nooooo");
        }
    }
    public int update2(int id,NotificationTemplat update)
    {
            UpdateToDataBaseWithPlaceHolder(id,update);

        return 1;
    }

}