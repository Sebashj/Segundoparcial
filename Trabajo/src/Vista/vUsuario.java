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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class vUsuario extends JFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtuser;
	private JTextField txtPassword;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	DaoUsuario dao=new DaoUsuario();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblusuario;
	ArrayList<Usuarion> lista = new ArrayList<Usuarion>();
	int fila=-1;
	Usuarion usuario;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vUsuario frame = new vUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtuser.setText("");
		txtPassword.setText("");
		txtnombre.setText("");
	}

	public vUsuario() {
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(vUsuario.class.getResource("/Img/nace un villano87.jpeg")));
		setTitle("CRUDUSUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 484);
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
					if(txtuser.getText().equals("")||txtPassword.getText().equals("")||txtnombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Usuarion user=new Usuarion();
					user.setUser(txtuser.getText());
					user.setPassword(txtPassword.getText());
					user.setNombre(txtnombre.getText());
					if (dao.insertarUsuario(user)) {
						refrescarTabla();
						limpiar();
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
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarUsuario(lista.get(fila).getId())) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se elimino correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnEliminar.setBounds(118, 162, 89, 23);
		contentPane.add(btnEliminar);
		
		btnBorrar = new JButton("borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				
			}
		});
		btnBorrar.setBounds(228, 162, 89, 23);
		contentPane.add(btnBorrar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtuser.getText().equals("")||txtPassword.getText().equals("")||txtnombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					usuario.setUser(txtuser.getText());
					usuario.setPassword(txtPassword.getText());
					usuario.setNombre(txtnombre.getText());
					if (dao.editarUsuario(usuario)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch (Exception e2) {
					
				}
				
			}
		});
		btnEditar.setBounds(329, 162, 89, 23);
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 197, 398, 238);
		contentPane.add(scrollPane);
		
		tblusuario = new JTable();
		tblusuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblusuario.getSelectedRow();
				usuario=lista.get(fila);
				lblid.setText(""+lista.get(fila).getId());
				txtuser.setText(""+usuario.getUser());
				txtPassword.setText(""+usuario.getPassword());
				txtnombre.setText(""+usuario.getNombre());
				
			}
		});
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
