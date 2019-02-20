package com.example.julio.socketplusqr;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MainActivity extends Activity {

	public static String direccionIp;
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void iniciarVentanaQR(View v) {
		EditText dirip = (EditText) findViewById(R.id.txtDirip);
		direccionIp = dirip.getText().toString();

		Intent ventanaqr = new Intent(MainActivity.this, ActivitiQR.class);
		startActivity(ventanaqr);



    }
	public void ActualizarInventario(View v) {
		EditText equipo= (EditText) findViewById(R.id.txtEquipo);
		equipo.setText(ActivitiQR.LECTOR_QR);
		String sql = "SELECT CpuInventario.Inventario AS Cpu, Equipo.ID, EquipoInventario.Inventario, ImpresoraInventario.Inventario AS Impresora, MicrofonoInventario.Inventario AS Microfono, MouseInventario.Inventario AS Mouse, \n" +
		"                         PantallaInventario.Inventario AS Pantalla, PantallaProyeccionInventario.Inventario AS PantallaProyeccion, RadioInventario.Inventario AS Radios, ProyectorInventario.Inventario AS Proyector, \n" +
				"                         ReguladorInventario.Inventario AS Regulador, TelefonoInventario.Inventario AS Telefono, TecladoInventario.Inventario AS Teclado, ParlanteInventario.Inventario AS Parlante, Propietario.Usuario, FechaInventario.Fecha, \n" +
				"                         Usuario.Nombre, Inventario.observacion, NombreOficina.NombreOficina\n" +
				"						  FROM Equipo INNER JOIN\n" +
				"                         Cpu ON Equipo.Cpu = Cpu.ID INNER JOIN\n" +
				"                         CpuInventario ON Cpu.Inventario = CpuInventario.ID INNER JOIN\n" +
				"                         EquipoInventario ON Equipo.Inventario = EquipoInventario.ID INNER JOIN\n" +
				"                         Impresora ON Equipo.Impresora = Impresora.ID INNER JOIN\n" +
				"                         ImpresoraInventario ON Impresora.Inventario = ImpresoraInventario.ID INNER JOIN\n" +
				"                         Microfono ON Equipo.Microfono = Microfono.ID INNER JOIN\n" +
				"                         MicrofonoInventario ON Microfono.Inventario = MicrofonoInventario.ID INNER JOIN\n" +
				"                         Mouse ON Equipo.Mouse = Mouse.ID INNER JOIN\n" +
				"                         MouseInventario ON Mouse.Inventario = MouseInventario.ID INNER JOIN\n" +
				"                         Pantalla ON Equipo.Pantalla = Pantalla.ID INNER JOIN\n" +
				"                         PantallaInventario ON Pantalla.Inventario = PantallaInventario.ID INNER JOIN\n" +
				"                         PantallaProyeccion ON Equipo.PantallaProyeccion = PantallaProyeccion.ID INNER JOIN\n" +
				"                         PantallaProyeccionInventario ON PantallaProyeccion.Inventario = PantallaProyeccionInventario.ID INNER JOIN\n" +
				"                         Parlante ON Equipo.Parlante = Parlante.ID INNER JOIN\n" +
				"                         ParlanteInventario ON Parlante.Inventario = ParlanteInventario.ID INNER JOIN\n" +
				"                         Proyector ON Equipo.Proyector = Proyector.ID INNER JOIN\n" +
				"                         ProyectorInventario ON Proyector.Inventario = ProyectorInventario.ID INNER JOIN\n" +
				"                         Radio ON Equipo.Radios = Radio.ID INNER JOIN\n" +
				"                         RadioInventario ON Radio.Inventario = RadioInventario.ID INNER JOIN\n" +
				"                         Regulador ON Equipo.Regulador = Regulador.ID INNER JOIN\n" +
				"                         ReguladorInventario ON Regulador.Inventario = ReguladorInventario.ID INNER JOIN\n" +
				"                         Teclado ON Equipo.Teclado = Teclado.ID INNER JOIN\n" +
				"                         TecladoInventario ON Teclado.Inventario = TecladoInventario.ID INNER JOIN\n" +
				"                         Telefono ON Equipo.Telefono = Telefono.ID INNER JOIN\n" +
				"                         TelefonoInventario ON Telefono.Inventario = TelefonoInventario.ID INNER JOIN\n" +
				"                         Propietario ON Equipo.Propietario = Propietario.ID INNER JOIN\n" +
				"                         Inventario ON Equipo.ID = Inventario.Equipo INNER JOIN\n" +
				"                         FechaInventario ON Inventario.Fecha = FechaInventario.ID INNER JOIN\n" +
				"                         Usuario ON Inventario.Ayudante = Usuario.ID INNER JOIN\n" +
				"                         Oficina ON Equipo.Oficina = Oficina.ID INNER JOIN\n" +
				"                         NombreOficina ON Oficina.NombreOficina = NombreOficina.ID\n" +
				"WHERE        (EquipoInventario.Inventario = '"+ActivitiQR.LECTOR_QR+"')";
		ResultSet result = Conectar.Consulta(sql);
		try {
			result.next();
			EditText num = (EditText) findViewById(R.id.txtCpu);
			num.setText(result.getString(1));

			EditText num1 = (EditText) findViewById(R.id.txtPantalla);
			num1.setText(result.getString(7));

			EditText num2 = (EditText) findViewById(R.id.txtMouse);
			num2.setText(result.getString(6));

			EditText num3 = (EditText) findViewById(R.id.txtTeclado);
			num3.setText(result.getString(13));

			EditText num4 = (EditText) findViewById(R.id.txtRegulador);
			num4.setText(result.getString(11));

			EditText num5= (EditText) findViewById(R.id.txtParlante);
			num5.setText(result.getString(14));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void ConnectToDatabase(){
		try {

			// SET CONNECTIONSTRING
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			String username = "Administrador";
			String password = "Fcnm2018";
			Connection DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.9:1433/SistemasFCNM;user=" + username + ";password=" + password);

			Log.w("Connection","open");
			Statement stmt = DbConn.createStatement();
			ResultSet reset = stmt.executeQuery(" select * from Usuario");
			reset.next();

			//EditText num = (EditText) findViewById(R.id.displaymessage2);
			//num.setText(reset.getString(2));

			DbConn.close();

		} catch (Exception e)
		{
			Log.w("Error connection","" + e.getMessage());
		}
	}



}
