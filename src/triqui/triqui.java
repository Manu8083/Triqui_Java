package triqui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class triqui extends JFrame implements ActionListener{

	/**
	 * 
	 */

	private JPanel contentPane;

	// Launch the application.
		
	casilla [][]TABLERO;
	int contador;
	
	
	
	// end
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					triqui frame = new triqui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public triqui() {
		setResizable(false);
		setTitle("Triqui By Manuel Romero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane}));
		
		initComponents();
		TABLERO = new casilla[3][3]; 
		for (int i=0; i<3; i++){
			
			for(int u=0; u<3; u++){
				TABLERO[i][u] = new casilla();
				TABLERO[i][u].A.setBounds((i*100)+10, (u*100)+10, 100, 100);
				TABLERO[i][u].A.addActionListener(this);
				getContentPane().add(TABLERO[i][u].A);
			}
		}
		
	}
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setLocation(new java.awt.Point(300, 200));
		setPreferredSize(new java.awt.Dimension(320, 340));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGap(0, 400, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGap(0, 300, Short.MAX_VALUE)
		);

		pack();
		}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i=0; i<3; i++ ){
			
			for(int u=0; u<3; u++){
				
				if (e.getSource()==TABLERO[i][u].A){
					tiro(TABLERO[i][u]);
					if(revisar()){
						JOptionPane.showMessageDialog(null, "Ganaste!!");
					}
					contador++; 
				};
				
			}
		}
	}
	
	//cargar imagen en el botton
	
	void tiro(casilla X){
		ImageIcon ICONO = null;
		if (contador %2==0){
			ICONO = new ImageIcon(this.getClass().getResource("x.png"));
			X.B = 1;
		}else{
			ICONO = new ImageIcon(this.getClass().getResource("c.jpg"));
			X.B = 4;
		}
		
		ICONO = new ImageIcon(ICONO.getImage().getScaledInstance(90, -1, java.awt.Image.SCALE_DEFAULT));
		X.A.setIcon(ICONO);
		X.A.removeActionListener(this);
	};
	
	boolean revisar(){
		boolean gano = false;
		int suma = 0;
		for (int i=0; i<3; i++){ //revisa las columnas
			suma=TABLERO[i][0].B+TABLERO[i][1].B+TABLERO[i][2].B;
			if(suma == 3 || suma == 12){
				gano = true;
				break;
			}
		}
		for (int i=0; i<3; i++){//revisa las filas
			suma=TABLERO[0][i].B+TABLERO[1][i].B+TABLERO[2][i].B;
			if(suma == 3 || suma == 12){
				gano = true;
				break;
			}
		}
		suma=TABLERO[0][2].B+TABLERO[1][1].B+TABLERO[2][0].B; //diagonal invertida
		if(suma == 3 || suma == 12)
			gano = true;
		suma = 0;
		for (int i=0; i<3; i++)
			suma+=TABLERO[i][i].B; //suma diagonal 0,0 1.1 2.2
		if(suma==3 || suma == 12)
			gano = true;	

		return gano;
	}
}
