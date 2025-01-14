package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.admin.CustomerDialogAdminController;
import help.ImageAccept;
import model.Customers;

@SuppressWarnings("serial")
public class CustomerAdminDialog extends JDialog {
	private AdminFrameView adminFrameView;
	private Customers customer;
	private byte[] customerIcon;

	private JTextField txtCustomerId;
	private JTextField txtFirstName;
	private JRadioButton rdbMale;
	private JRadioButton rdbFemale;
	private JTextField txtLastName;
	private JFormattedTextField ftxtBirthDay;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JButton btnBack;
	private JLabel lbIcon;

	public CustomerAdminDialog(AdminFrameView adminFrameView, Customers customer) {
		super(adminFrameView, true);
		this.adminFrameView = adminFrameView;
		this.customer = customer;
		initComponent();
		setEvent();
		if (customer != null) {
			initInformation();
		}
		pack();
		setTitle("Customer View");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initComponent() {
		// component

		Font font = new Font("Arial", Font.PLAIN, 16);
		JLabel lbCustomerId = new JLabel("Customer Id");
		lbCustomerId.setFont(font);
		txtCustomerId = new JTextField(10);
		txtCustomerId.setFont(font);
		lbIcon = new JLabel(new ImageIcon(getClass().getResource("/icon/person72.png")));
		lbIcon.setBorder(new LineBorder(Color.CYAN));
		lbIcon.setPreferredSize(new Dimension(100, 100));

		JLabel lbFirstName = new JLabel("FirstName");
		lbFirstName.setFont(font);
		txtFirstName = new JTextField(20);
		txtFirstName.setFont(font);
		JLabel lbLastName = new JLabel("Last Name");
		lbLastName.setFont(font);
		txtLastName = new JTextField(20);
		txtLastName.setFont(font);
		JLabel lbSex = new JLabel("Sex");
		lbSex.setFont(font);
		rdbMale = new JRadioButton("Male");
		rdbMale.setFont(font);
		rdbFemale = new JRadioButton("Female");
		rdbFemale.setFont(font);
		JLabel lbBirthDay = new JLabel("Birth Day");
		lbBirthDay.setFont(font);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		sdf.setLenient(false);
		ftxtBirthDay = new JFormattedTextField(sdf);
		ftxtBirthDay.setFont(font);
		JLabel lbPhone = new JLabel("Phone");
		lbPhone.setFont(font);
		txtPhone = new JTextField(10);
		txtPhone.setFont(font);
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(font);
		txtEmail = new JTextField(10);
		txtEmail.setFont(font);
		JLabel lbAddress = new JLabel("Address");
		lbAddress.setFont(font);
		txtAddress = new JTextField(10);
		txtAddress.setFont(font);

		btnBack = new JButton("Back", new ImageIcon(getClass().getResource("/icon/Go-back-icon16.png")));
		btnBack.setFont(font);

		// component status

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbMale);
		buttonGroup.add(rdbFemale);
		rdbMale.setSelected(true);

		rdbFemale.setEnabled(false);
		rdbMale.setEnabled(false);
		txtCustomerId.setEditable(false);
		txtFirstName.setEditable(false);
		txtLastName.setEditable(false);
		ftxtBirthDay.setEditable(false);
		txtEmail.setEditable(false);
		txtPhone.setEditable(false);
		txtAddress.setEditable(false);
		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weighty = 1;
		gbc.weightx = 1;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		add(lbCustomerId, gbc);

		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(txtCustomerId, gbc);

		gbc.gridx = 3;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		add(lbIcon, gbc);

		// thêm label
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(lbFirstName, gbc);

		gbc.gridy++;
		add(lbSex, gbc);

		gbc.gridy++;
		add(lbPhone, gbc);

		gbc.gridy++;
		add(lbAddress, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		add(lbLastName, gbc);

		gbc.gridy++;
		add(lbBirthDay, gbc);

		gbc.gridy++;
		add(lbEmail, gbc);

		// thêm txt+btn
		gbc.gridx = 1;
		gbc.gridy--;
		add(rdbMale, gbc);

		gbc.gridx++;
		add(rdbFemale, gbc);

		gbc.gridx = 4;
		gbc.gridy = 1;
		add(txtLastName, gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(ftxtBirthDay, gbc);

		gbc.gridy++;
		add(txtEmail, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		add(txtFirstName, gbc);

		gbc.gridy += 2;
		add(txtPhone, gbc);

		gbc.gridwidth = 1;

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		add(txtAddress, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(btnBack, gbc);
	}

	private void initInformation() {
		// infomation
		txtCustomerId.setText(customer.getCustomerId().toString());
		txtFirstName.setText(customer.getFirstName());
		txtLastName.setText(customer.getLastName());
		if (customer.getSex()) {
			rdbMale.setSelected(true);
		} else {
			rdbFemale.setSelected(true);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if (customer.getBirthDay() != null) {
			String dateFormat = sdf.format(customer.getBirthDay());
			ftxtBirthDay.setText(dateFormat);
		}
		txtPhone.setText(customer.getPhone());
		txtEmail.setText(customer.getEmail());
		txtAddress.setText(customer.getAddress());
		if (customer.getCustomerIcon() != null) {
			setCustomerIcon(customer.getCustomerIcon());
			try {
				Image img = ImageAccept.createImageFromByteArray(getCustomerIcon(), "jpg");
				Image resize = ImageAccept.resize(img, 100, 100);
				ImageIcon icon = new ImageIcon(resize);
				lbIcon.setIcon(icon);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void setEvent() {
		CustomerDialogAdminController controller = new CustomerDialogAdminController(this);
		btnBack.addActionListener(controller);
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public byte[] getCustomerIcon() {
		return customerIcon;
	}

	public void setCustomerIcon(byte[] customerIcon) {
		this.customerIcon = customerIcon;
	}

	public AdminFrameView getAdminFrameView() {
		return adminFrameView;
	}

	public JTextField getTxtCustomerId() {
		return txtCustomerId;
	}

	public JTextField getTxtFirstName() {
		return txtFirstName;
	}

	public JRadioButton getRdbMale() {
		return rdbMale;
	}

	public JRadioButton getRdbFemale() {
		return rdbFemale;
	}

	public JTextField getTxtLastName() {
		return txtLastName;
	}

	public JFormattedTextField getFtxtBirthDay() {
		return ftxtBirthDay;
	}

	public JTextField getTxtPhone() {
		return txtPhone;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public JLabel getLbIcon() {
		return lbIcon;
	}

}
