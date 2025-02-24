package presentacion.GUIFacturas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaBuscarFactura extends JDialog implements IGUI{

	private JTextField _view;
	
	public VistaBuscarFactura(){
		super();
		IGUI();
	}
	
	private void IGUI() {
		setTitle("Buscar de Factura");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
		setContentPane(mainPanel);
		setModal(true);
		JLabel texto = new JLabel("ID Factura");
		mainPanel.add(texto);
		_view = new JTextField();
		_view.setPreferredSize(new Dimension(150,20));
		_view.setMaximumSize(new Dimension(150,20));
		_view.setMinimumSize(new Dimension(150,20));
		mainPanel.add(_view);
		JButton mostrar = new JButton("Ver Factura");
		mostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstancia().accion(Eventos.BUSCAR_FACTURA, _view.getText());
				} catch (FileNotFoundException | JSONException e1) {
					e1.printStackTrace();
				}
			}
		});
		mainPanel.add(mostrar);
		setPreferredSize(new Dimension(320,100));
		pack();
		setVisible(false);		
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.BUSCAR_FACTURA:
			setVisible(true);
			break;
		case Eventos.RES_BUSCAR_FACTURA_NO:
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar una factura con ese id","Error",JOptionPane.ERROR_MESSAGE);
		}		
	}

}
