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

import Dao.DaoAlumno;
import Modelo.Alumno;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class vAlumno extends JFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtcorreo;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	DaoAlumno dao=new DaoAlumno();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblalumno;
	ArrayList<Alumno> lista = new ArrayList<Alumno>();
	int fila=-1;
	Alumno alumno;
	private JLabel lblNewLabel_2;
	private JComboBox cboSemestre;
	private JComboBox cboGrupo;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vAlumno frame = new vAlumno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtcorreo.setText("");
		txtnombre.setText("");
	}

	public vAlumno() {
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(vAlumno.class.getResource("/Img/nace un villano87.jpeg")));
		setTitle("CRUDALUMNO");
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
		
		JLabel lblNewLabel_1 = new JLabel("Semestre");
		lblNewLabel_1.setBounds(10, 94, 53, 23);
		contentPane.add(lblNewLabel_1);
		
		txtcorreo = new JTextField();
		txtcorreo.setBounds(73, 129, 86, 20);
		contentPane.add(txtcorreo);
		txtcorreo.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Correo");
		lblNewLabel_1_1.setBounds(10, 128, 53, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setBounds(10, 60, 53, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(73, 60, 86, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtcorreo.getText().equals("")||cboGrupo.getSelectedItem().equals("")||txtnombre.getText().equals("")||cboSemestre.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Alumno user=new Alumno();
					user.setNombre(txtnombre.getText());
					user.setGrupo(Integer.parseInt(cboSemestre.getSelectedItem().toString()));
					user.setCorreo(txtcorreo.getText());
					user.setSemestre(cboSemestre.getSelectedItem().toString());
					
					if (dao.insertarAlumno(user)) {
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
					if (dao.eliminarAlumno(lista.get(fila).getId())) {
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
					if(txtcorreo.getText().equals("")||cboGrupo.getSelectedItem().equals("")||txtnombre.getText().equals("")||cboSemestre.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					alumno.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					alumno.setNombre(txtnombre.getText());
					alumno.setCorreo(txtcorreo.getText());
					alumno.setSemestre(cboSemestre.getSelectedItem().toString());
					if (dao.editarAlumno(alumno)) {
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
		
		tblalumno = new JTable();
		tblalumno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblalumno.getSelectedRow();
				alumno=lista.get(fila);
				lblid.setText(""+lista.get(fila).getId());
				txtcorreo.setText(""+alumno.getCorreo());
				txtnombre.setText(""+alumno.getNombre());
				cboGrupo.setSelectedItem(""+alumno.getGrupo());
				cboSemestre.setSelectedItem(""+alumno.getSemestre());
				
			}
		});
		tblalumno.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblalumno);
		
		modelo.addColumn("ID");
		modelo.addColumn("nombre");
		modelo.addColumn("grupo");
		modelo.addColumn("correo");
		modelo.addColumn("semestre");
		tblalumno.setModel(modelo);
		refrescarTabla();
		
		lblNewLabel_2 = new JLabel("Grupo");
		lblNewLabel_2.setBounds(176, 26, 60, 23);
		contentPane.add(lblNewLabel_2);
		
		cboSemestre = new JComboBox();
		cboSemestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Primero", "Tercero", "Quinto"}));
		cboSemestre.setBounds(73, 94, 86, 22);
		contentPane.add(cboSemestre);
		
		cboGrupo = new JComboBox();
		cboGrupo.setModel(new DefaultComboBoxModel(new String[] {"105", "205", "305"}));
		cboGrupo.setBounds(228, 26, 76, 22);
		contentPane.add(cboGrupo);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchAlumnos();
		for(Alumno u: lista) {
			Object o[]=new Object [5];
			o[0]=u.getId();
			o[1]=u.getCorreo();
			o[2]=u.getGrupo();
			o[3]=u.getNombre();
			o[4]=u.getSemestre();
			modelo.addRow(o);
		}
		tblalumno.setModel(modelo);
	}
}