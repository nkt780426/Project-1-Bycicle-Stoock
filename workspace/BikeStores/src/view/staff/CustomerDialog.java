package view.staff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import controller.staff.CustomerDialogController;

@SuppressWarnings("serial")
public class CustomerDialog extends JDialog {
	private StaffFrameView staffFrameView;
	public byte[] customerIcon;

	public JTextField txtCustomerId;
	public JTextField txtFirstName;
	public JRadioButton rdbMale;
	public JRadioButton rdbFemale;
	public JTextField txtLastName;
	public JFormattedTextField ftxtBirthDay;
	public JTextField txtPhone;
	public JTextField txtEmail;
	public JTextField txtAddress;
	public JButton btnOpen;
	public JButton btnBack;
	public JButton btnTransport;
	public JLabel lbIcon;

	public CustomerDialog(StaffFrameView staffFrameView) {
		super(staffFrameView, true);
		this.staffFrameView = staffFrameView;
		initComponent();
		pack();
		setLocationRelativeTo(null);
		setTitle("Order Dialog");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setEvent();
		setVisible(true);
	}

	private void setEvent() {
		CustomerDialogController controller = new CustomerDialogController(this);
		txtFirstName.addFocusListener(controller);
		txtLastName.addFocusListener(controller);
		txtPhone.addFocusListener(controller);
		txtEmail.addFocusListener(controller);
		txtAddress.addFocusListener(controller);
		btnOpen.addActionListener(controller);
		btnBack.addActionListener(controller);
		btnTransport.addActionListener(controller);
		btnTransport.addFocusListener(controller);
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

		JLabel lbFirstName = new JLabel("FirstName(*)");
		lbFirstName.setFont(font);
		txtFirstName = new JTextField(10);
		txtFirstName.setFont(font);
		JLabel lbLastName = new JLabel("Last Name(*)");
		lbLastName.setFont(font);
		txtLastName = new JTextField(10);
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
		JLabel lbPhone = new JLabel("Phone(*)");
		lbPhone.setFont(font);
		txtPhone = new JTextField(10);
		txtPhone.setFont(font);
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(font);
		txtEmail = new JTextField(10);
		txtEmail.setFont(font);
		JLabel lbAddress = new JLabel("Address(*)");
		lbAddress.setFont(font);
		txtAddress = new JTextField(10);
		txtAddress.setFont(font);

		btnOpen = new JButton("Open", new ImageIcon(getClass().getResource("/icon/open-file-icon16.png")));
		btnOpen.setFont(font);
		btnBack = new JButton("Back", new ImageIcon(getClass().getResource("/icon/Go-back-icon16.png")));
		btnBack.setFont(font);
		btnTransport = new JButton("Transport", new ImageIcon(getClass().getResource("/icon/truck-icon16.png")));
		btnTransport.setFont(font);

		// component status

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbMale);
		buttonGroup.add(rdbFemale);
		rdbMale.setSelected(true);

		txtCustomerId.setEditable(false);
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

		gbc.gridy++;
		add(lbFirstName, gbc);

		gbc.gridy++;
		add(lbLastName, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(txtCustomerId, gbc);

		gbc.gridy++;
		add(txtFirstName, gbc);

		gbc.gridy++;
		add(txtLastName, gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		add(lbIcon, gbc);

		gbc.gridy = 2;
		gbc.gridheight = 1;
		add(btnOpen, gbc);

		// thêm label
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(lbSex, gbc);

		gbc.gridy++;
		add(lbPhone, gbc);

		gbc.gridy++;
		add(lbAddress, gbc);

		gbc.gridx = 3;
		gbc.gridy = 3;
		add(lbBirthDay, gbc);

		gbc.gridy++;
		add(lbEmail, gbc);

		// thêm txt+btn
		gbc.gridx = 1;
		gbc.gridy--;
		gbc.gridwidth = 1;
		add(rdbMale, gbc);

		gbc.gridx++;
		add(rdbFemale, gbc);

		gbc.gridx = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(ftxtBirthDay, gbc);

		gbc.gridy++;
		add(txtEmail, gbc);

		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(txtPhone, gbc);

		gbc.gridx = 1;
		gbc.gridy++;
		gbc.gridwidth = 4;
		add(txtAddress, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(btnBack, gbc);

		gbc.gridx = 4;
		gbc.anchor = GridBagConstraints.EAST;
		add(btnTransport, gbc);
	}

	public byte[] getCustomerIcon() {
		return customerIcon;
	}

	public void setCustomerIcon(byte[] customerIcon) {
		this.customerIcon = customerIcon;
	}

	public StaffFrameView getStaffFrameView() {
		return staffFrameView;
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

	public JButton getBtnOpen() {
		return btnOpen;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public JButton getBtnTransport() {
		return btnTransport;
	}

	public JLabel getLbIcon() {
		return lbIcon;
	}

}
