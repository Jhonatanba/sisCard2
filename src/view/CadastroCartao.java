package view;

import br.com.vinicius.componente.JCustomInteger;
import br.com.vinicius.componente.JCustomNumberFormat;
import controller.CartaoController;
import dao.CartaoDao;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Cartao;
import utils.TableCartao;
import javax.swing.JRadioButton;

public class CadastroCartao extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBandeira;
	private JCustomInteger txtNVezes;
	private JCustomNumberFormat txtTaxa;
	private int flagAlterar = 0;
	private String codigo = null;
	private int achou = 0;
	private List<Cartao> cartaoTodos;
	private JTable table;
	private int nVezes;
	private String cartaoTipo;
	private JRadioButton rdbDebito;
	private JRadioButton rdbCredito;
	private String tipo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCartao frame = new CadastroCartao();
					
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void refreshTable() {
		CartaoDao dao = new CartaoDao();
		try {
			this.cartaoTodos = dao.findAll();
			this.table.setModel(TableCartao.tabelaCartao(this.cartaoTodos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CadastroCartao() {
		setTitle("Cadastro de Cartões");
		setResizable(false);
		setDefaultCloseOperation(2);
		setBounds(100, 100, 688, 350);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JLabel lblBandeira = new JLabel("Bandeira:");
		lblBandeira.setBounds(12, 12, 70, 15);
		this.contentPane.add(lblBandeira);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(140, 12, 70, 15);
		this.contentPane.add(lblTipo);

		JLabel lblTaxa = new JLabel("Taxa(%):");
		lblTaxa.setBounds(423, 12, 70, 15);
		this.contentPane.add(lblTaxa);

		this.table = new JTable();
		this.table.setRowSelectionAllowed(false);
		this.table.setFont(new Font("Dialog", 1, 12));

		JScrollPane scrollPane = new JScrollPane(this.table);
		scrollPane.setBounds(12, 90, 660, 161);
		this.contentPane.add(scrollPane);
		scrollPane.setViewportView(this.table);

		this.txtBandeira = new JTextField();
		this.txtBandeira.setBounds(12, 31, 114, 23);
		this.contentPane.add(this.txtBandeira);
		this.txtBandeira.setColumns(10);
		this.txtNVezes = new JCustomInteger();
		txtNVezes.setText("");

		this.txtNVezes.setBounds(298, 31, 114, 23);
		this.contentPane.add(this.txtNVezes);
		this.txtNVezes.setColumns(10);

		this.txtTaxa = new JCustomNumberFormat();
		this.txtTaxa.setBounds(422, 31, 114, 23);
		this.contentPane.add(this.txtTaxa);
		this.txtTaxa.setColumns(10);

		JButton btnNovo = new JButton("Novo");

		btnNovo.setBounds(162, 262, 118, 48);
		this.contentPane.add(btnNovo);
		rdbDebito = new JRadioButton("Débito");
		rdbCredito = new JRadioButton("Crédito");
		final JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCartao.this.achou = 0;
				if (CadastroCartao.this.txtBandeira.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Entre com a bandeira do cartão");
					CadastroCartao.this.achou = 1;
					CadastroCartao.this.txtBandeira.setBackground(new Color(255, 204, 204));
				} else {
					CadastroCartao.this.txtBandeira.setBackground(new Color(255, 255, 255));
				}
				if ((CadastroCartao.this.txtTaxa.getText().equals(""))
						|| (CadastroCartao.this.txtTaxa.getText().equals("0.00"))) {
					JOptionPane.showMessageDialog(null, "Entre com a taxa do cartão");
					CadastroCartao.this.achou = 1;
					CadastroCartao.this.txtTaxa.setBackground(new Color(255, 204, 204));
				} else {
					CadastroCartao.this.txtTaxa.setBackground(new Color(255, 255, 255));
				}

				if (rdbCredito.isSelected()) {
					cartaoTipo = "C";
				} else if (rdbDebito.isSelected()) {
					cartaoTipo = "D";
				} else {
					CadastroCartao.this.achou = 1;
					JOptionPane.showMessageDialog(null, "Selecione a opção de pagamento!");

				}

				if (txtNVezes.getText().equals("") || txtNVezes.getText().equals(null)) {
					nVezes = 1;
				} else if (Integer.parseInt(txtNVezes.getText()) > 0) {
					nVezes = Integer.parseInt(txtNVezes.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Campo n° de vezes incorreto!");
				}
				if (CadastroCartao.this.achou == 0) {
					CartaoController cc = new CartaoController();
					cc.salvar(CadastroCartao.this.txtBandeira.getText(), cartaoTipo, nVezes,
							CadastroCartao.this.txtTaxa.bigDecimalValue());
					CadastroCartao.this.txtBandeira.setText("");
					CadastroCartao.this.txtTaxa.setText("0.00");
					CadastroCartao.this.txtNVezes.setText("");
					CadastroCartao.this.refreshTable();
				}
			}
		});
		btnSalvar.setBounds(12, 262, 118, 48);
		this.contentPane.add(btnSalvar);
		final JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCartao.this.codigo = JOptionPane.showInputDialog(null, "Entre com o código do cartão",
						"Busca por código", 3);
				if ((CadastroCartao.this.codigo != null) || (!CadastroCartao.this.codigo.equals(""))) {
					CartaoController cc = new CartaoController();
					cc.excluir(Long.valueOf(Long.parseLong(CadastroCartao.this.codigo)));
					CadastroCartao.this.refreshTable();
				}
			}
		});

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon("src/img/Pencil-icon.png"));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CadastroCartao.this.flagAlterar == 0) {
					btnSalvar.setEnabled(false);
					btnExcluir.setEnabled(false);

					CadastroCartao.this.codigo = JOptionPane.showInputDialog(null, "Entre com o código do cartão",
							"Busca por código", 3);

					if (CadastroCartao.this.codigo != null) {
						try {
							CartaoDao dao = new CartaoDao();
							Cartao cartao = (Cartao) dao.findById(Long.parseLong(CadastroCartao.this.codigo));
							tipo = cartao.getCartaoTipo();
							if (tipo.equals("C")) {
								rdbCredito.setSelected(true);
								rdbDebito.setSelected(false);
							} else {
								rdbCredito.setSelected(false);
								rdbDebito.setSelected(true);
							}

							CadastroCartao.this.txtBandeira.setText(cartao.getCartaoBandeira());
							CadastroCartao.this.txtNVezes.setValue(cartao.getCartaoVezes());
							CadastroCartao.this.txtTaxa.setValue(cartao.getCartaoTaxa());

							CadastroCartao.this.flagAlterar = 1;
						} catch (Exception es) {
							es.printStackTrace();
							JOptionPane.showMessageDialog(null, "Cartao inexistente!");
						}
					} else {
						btnSalvar.setEnabled(true);
						btnExcluir.setEnabled(true);
					}
				} else {
					CartaoController cc = new CartaoController();
					if (rdbDebito.isSelected()) {
						tipo = "D";
						txtNVezes.setText("1");
					} else {
						tipo = "C";
					}
					cc.alterar(CadastroCartao.this.codigo, CadastroCartao.this.txtBandeira.getText(), tipo,
							CadastroCartao.this.txtNVezes.getText(), CadastroCartao.this.txtTaxa.bigDecimalValue());

					btnSalvar.setEnabled(true);
					btnExcluir.setEnabled(true);
					CadastroCartao.this.txtBandeira.setText("");
					CadastroCartao.this.txtTaxa.setText("0.00");
					CadastroCartao.this.txtNVezes.setText("");

					CadastroCartao.this.flagAlterar = 0;
				}
				CadastroCartao.this.refreshTable();
			}
		});
		btnAlterar.setBounds(292, 262, 118, 48);
		this.contentPane.add(btnAlterar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("src/img/cancel.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCartao.this.achou = 0;
				CadastroCartao.this.txtBandeira.setText("");
				CadastroCartao.this.txtTaxa.setText("0.00");
				CadastroCartao.this.txtNVezes.setText("");
				CadastroCartao.this.txtBandeira.setBackground(new Color(255, 255, 255));
				CadastroCartao.this.txtTaxa.setBackground(new Color(255, 255, 255));
				CadastroCartao.this.txtNVezes.setBackground(new Color(255, 255, 255));
				btnSalvar.setEnabled(true);
				btnExcluir.setEnabled(true);
				CadastroCartao.this.flagAlterar = 0;
				CadastroCartao.this.refreshTable();
			}
		});
		btnSalvar.setIcon(new ImageIcon(this.getClass().getResource( "/img/save.png" )));
		btnExcluir.setIcon(new ImageIcon("src/img/trash.png"));
		btnNovo.setIcon(new ImageIcon("src/img/new.png"));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCartao.this.achou = 0;
				CadastroCartao.this.txtBandeira.setText("");
				CadastroCartao.this.txtTaxa.setText("0.00");
				CadastroCartao.this.txtNVezes.setText("");
				CadastroCartao.this.txtBandeira.setBackground(new Color(255, 255, 255));
				CadastroCartao.this.txtTaxa.setBackground(new Color(255, 255, 255));
				CadastroCartao.this.txtNVezes.setBackground(new Color(255, 255, 255));
				btnSalvar.setEnabled(true);
				btnExcluir.setEnabled(true);
				CadastroCartao.this.flagAlterar = 0;
				CadastroCartao.this.refreshTable();
			}
		});
		btnCancelar.setBounds(422, 262, 118, 48);
		this.contentPane.add(btnCancelar);

		btnExcluir.setBounds(552, 262, 118, 48);
		this.contentPane.add(btnExcluir);

		JButton btnAtualizarTabela = new JButton("Atualizar ");
		btnAtualizarTabela.setIcon(new ImageIcon("src/img/Refresh-icon.png"));
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCartao.this.refreshTable();
			}
		});
		btnAtualizarTabela.setBounds(546, 31, 126, 48);
		this.contentPane.add(btnAtualizarTabela);

		JLabel lblNVezes = new JLabel("N° de parcelas:");
		lblNVezes.setBounds(298, 12, 115, 14);
		contentPane.add(lblNVezes);

		rdbDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbCredito.isSelected()) {
					rdbCredito.setSelected(false);
					txtNVezes.setText("");
				}
				txtNVezes.setEnabled(false);
			}
		});

		rdbCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbDebito.isSelected()) {
					rdbDebito.setSelected(false);

				}
				txtNVezes.setEnabled(true);

			}
		});
		rdbCredito.setBounds(140, 31, 70, 23);
		contentPane.add(rdbCredito);

		rdbDebito.setBounds(214, 31, 73, 23);
		contentPane.add(rdbDebito);

		refreshTable();
		setLocationRelativeTo(null);
	}
}
