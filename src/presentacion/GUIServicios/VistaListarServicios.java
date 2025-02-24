package presentacion.GUIServicios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.json.JSONException;
import org.json.JSONObject;

import negocio.TServicios;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

@SuppressWarnings("serial")
public class VistaListarServicios extends JDialog implements IGUI{
	
	private JPanel mainPanel;
	
	public VistaListarServicios() {
		initGUI();
		
	}
	
	private void initGUI() {
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		setModal(true);
		JLabel message=new JLabel("¿Seguro que quieres listar los servicios?");
		mainPanel.add(message);
		JPanel buttons=new JPanel();
		JButton ok=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		buttons.add(ok);
		buttons.add(cancelar);
		mainPanel.add(buttons);
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				try {
					Controlador.getInstancia().accion(Eventos.LISTAR_SERVICIO, null);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				
			}
			
		});
		pack();
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch(evento) {
		case Eventos.LISTAR_SERVICIO:{
			setVisible(true);
			break;
		}case Eventos.RES_LISTAR_SERVICIOS_OK:{
			if(datos!=null) {
				List<TServicios> l= (List<TServicios>) datos;
				mainPanel.removeAll();
				setTitle("Lista de Servicios");
				mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
				setContentPane(mainPanel);
				String[] columnas= {"Id","Nombre","Subtipo","Plazas","Precio"};
				List<Object[]> data = new ArrayList<>();
				for(int i=0;i<l.size();i++) {
					JSONObject o=l.get(i).escribirServicioId();
					data.add(new Object[] {o.getString("Id"),o.getString("Nombre servicio"),
							o.getString("Subtipo"),o.get("Número de plazas disponibles").toString(),
							o.get("Precio de cada plaza").toString()});
				}
				 DefaultTableModel modelo = new DefaultTableModel() {
					 @Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
				 };
				 modelo.setColumnIdentifiers(columnas);
				 for (Object[] dato : data) {
			            modelo.addRow(dato);
			        }
				 JTable tabla = new JTable(modelo){

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
						@Override
					       public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
					           Component component = super.prepareRenderer(renderer, row, column);
					           int rendererWidth = component.getPreferredSize().width;
					           TableColumn tableColumn = getColumnModel().getColumn(column);
					           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
					           return component;
					        }
				};
				tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane panelDesplazable = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				JLabel listaLabel = new JLabel("Lista de los servicios:");
				JPanel btn=new JPanel();
				JButton aceptarBtn = new JButton("Aceptar");
				btn.add(aceptarBtn);
				aceptarBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setVisible(false);
					}
					 
				});
				mainPanel.add(listaLabel, BorderLayout.NORTH);
			    mainPanel.add(panelDesplazable, BorderLayout.CENTER);
			    mainPanel.add(btn, BorderLayout.SOUTH);
			    pack();
			    setResizable(false);
			    setVisible(true);
				
				break;
			}else {
				JOptionPane.showMessageDialog(this, "Ha ocurrido un error al listar los servicios","ERROR",JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		}
	}

}
