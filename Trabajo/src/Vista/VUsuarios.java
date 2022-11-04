package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.DaoUsuario;
import Modelo.Usuarion;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VUsuarios extends JFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtuser;
	private JTextField txtPassword;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBorrar;
	DaoUsuario dao=new DaoUsuario();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblusuario;
	ArrayList<Usuarion> lista = new ArrayList<Usuarion>();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VUsuarios frame = new VUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VUsuarios() {
		setTitle("CRUDUSUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(73, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(10, 94, 53, 23);
		contentPane.add(lblNewLabel_1);
		
		txtuser = new JTextField();
		txtuser.setBounds(73, 60, 86, 20);
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario");
		lblNewLabel_1_1.setBounds(10, 60, 53, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(73, 95, 86, 20);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setBounds(10, 128, 53, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(73, 126, 86, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuarion user=new Usuarion();
					user.setUser(txtuser.getText());
					user.setPassword(txtPassword.getText());
					user.setNombre(txtnombre.getText());
					if (dao.insertarUsuario(user)) {
						refrescarTabla();
						JOptionPane.showMessageDialog(null, "Se agrego correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnAgregar.setBounds(10, 162, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("editar");
		btnEditar.setBounds(118, 162, 89, 23);
		contentPane.add(btnEditar);
		
		btnBorrar = new JButton("borrar");
		btnBorrar.setBounds(228, 162, 89, 23);
		contentPane.add(btnBorrar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(329, 162, 89, 23);
		contentPane.add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 197, 398, 238);
		contentPane.add(scrollPane);
		
		tblusuario = new JTable();
		tblusuario.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblusuario);
		
		modelo.addColumn("ID");
		modelo.addColumn("USERT");
		modelo.addColumn("PASSWORD");
		modelo.addColumn("NOMBRE");
		tblusuario.setModel(modelo);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchUsuarios();
		for(Usuarion u: lista) {
			Object o[]=new Object [4];
			o[0]=u.getId();
			o[1]=u.getUser();
			o[2]=u.getPassword();
			o[3]=u.getNombre();
			modelo.addRow(o);
		}
		tblusuario.setModel(modelo);
	}
}
