package work;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class Work extends JFrame
{
    final String usuario = "root";
    final String password = "";
    Connection dbConnection = null;
    Statement statement = null;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL_CONEXION = "jdbc:mysql://localhost/test_db";

    JLabel JL_id, JL_username, JL_password, JL_name;
    JTextField JT_id, JT_username, JT_password, JT_name;
    JButton btn_consult, btn_insert, btn_update, btn_delete;

    public Work()
    {
        super("CRUD");
        JL_id = new JLabel("ID:");
        JL_username = new JLabel("USERNAME:");
        JL_password = new JLabel("PASSWORD:");
        JL_name = new JLabel("NAME:");
        JL_id.setBounds(20, 20, 100, 20);
        JL_username.setBounds(20, 50, 100, 20);
        JL_password.setBounds(20, 80, 100, 20);
        JL_name.setBounds(20, 110, 100, 20);

        JT_id = new JTextField(20);
        JT_username = new JTextField(20);
        JT_password = new JTextField(20);
        JT_name = new JTextField(20);
        JT_id.setBounds(130, 20, 150, 20);
        JT_username.setBounds(130, 50, 150, 20);
        JT_password.setBounds(130, 80, 150, 20);
        JT_name.setBounds(130, 110, 150, 20);
        btn_consult = new JButton("Consult");
        btn_insert = new JButton("Insert");
        btn_update = new JButton("Update");
        btn_delete = new JButton("Delete");
        btn_consult.setBounds(300, 20, 80, 20);
        btn_insert.setBounds(300, 50, 80, 20);
        btn_update.setBounds(300, 80, 80, 20);
        btn_delete.setBounds(300, 110, 80, 20);

        setLayout(null);
        add(JL_id);
        add(JL_username);
        add(JL_password);
        add(JL_name);
        add(JT_id);
        add(JT_username);
        add(JT_password);
        add(JT_name);
        add(btn_consult);
        add(btn_insert);
        add(btn_update);
        add(btn_delete);

        //button consult
        btn_consult.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    //theQuery("select * from users values('" + JT_username.getText() + "','" + JT_password.getText() + "'," + JT_name.getText() + ")");
                    Class.forName(DRIVER);
                    
                    Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                    String selectTableSQL = "SELECT * FROM users";
                    statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(selectTableSQL);
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String usr = rs.getString("username");
                        String pws = rs.getString("password");
                        String nombre = rs.getString("name");

                        System.out.println("id: " + id);
                        System.out.println("username: " + usr);
                        System.out.println("password: " + pws);
                        System.out.println("name: " + nombre);
                    }
                } catch (Exception ex) {
                }
            }
        });
        //button insert
        btn_insert.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    theQuery("insert into users (username,password,name) values('" + JT_username.getText() + "','" + JT_password.getText() + "','" + JT_name.getText() + "')");
                } catch (Exception ex)
                {
                }
            }
        });

        //button update
        btn_update.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    theQuery("update users set username = '" + JT_username.getText() + "',password = '" + JT_password.getText() + "', name = '" + JT_name.getText() + "' where id = " + JT_id.getText());
                } catch (Exception ex){}
            }
        });

        //button delete
        btn_delete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    theQuery("delete from users where id = " + JT_id.getText());
                } catch (Exception ex)
                {
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500, 200);
    }

    //function to execute the insert update delete query
    public void theQuery(String query)
    {
        Connection con = null;
        Statement st = null;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/test_db", "root", "");
            st = con.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "¡Query Realizado!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void main(String[] args)
    {
        new Work();
    }
}


//Consulta todo en consola