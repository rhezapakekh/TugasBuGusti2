import java.sql.*;
import java.util.Scanner;
public class Koneksi {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sekolah";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static void create(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("masukan nama siswa :");
            String nama= input.next();
            System.out.println("masukan alamat siswa");
            String alamat= input.next();

            String sql = "INSERT INTO siswa (nama_siswa, alamat_siswa) VALUE ('%s','%s')";
            sql= String.format(sql,nama,alamat);

            stmt.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void read(){
        String sql = "SELECT * FROM siswa";
        try{
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id_siswa = rs.getInt("id_siswa");
                String nama_siswa = rs.getString("nama_siswa");
                String alamat_siswa = rs.getString("alamat_siswa");

                System.out.println(String.format("%d. nama : %s, alamat : %s",id_siswa,nama_siswa,alamat_siswa));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void update(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("masukan id siswa yang ingin di update :");
            int id = input.nextInt();
            System.out.println("masukan nama siswa baru :");
            String nama=input.next();
            System.out.println("masukan alamat baru :");
            String alamat=input.next();

            String sql="UPDATE siswa SET nama_siswa='%s', alamat_siswa='%s' WHERE id_siswa=%d";
            sql = String.format(sql, nama,alamat,id);

            stmt.execute(sql);
            System.out.println("data telah di update");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void delete(){
        try{
            Scanner input= new Scanner(System.in);

            System.out.println("masukan id yang ingin dihapus :");
            int id=input.nextInt();

            String sql = String.format("DELETE FROM siswa WHERE id_siswa = %d",id);
            stmt.execute(sql);

            System.out.println("data telah dihapus");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER,PASS);
            stmt=conn.createStatement();

            while(!conn.isClosed()){
                System.out.println("1. Create" +
                        "\n2. Read" +
                        "\n3. Update" +
                        "\n4. Delete" +
                        "\n5. exit");
                System.out.println("masukan pilihan :");
                int pilih = input.nextInt();

                if(pilih==1){
                    create();
                }else if(pilih==2){
                    read();
                }else if(pilih==3){
                    update();
                }else if(pilih==4){
                    delete();
                }else{
                    System.exit(0);
                }
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
