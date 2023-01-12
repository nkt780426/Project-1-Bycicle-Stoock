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
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.admin.StaffsDialogController;
import dao.StaffsDao;
import dao.StoresDao;
import help.ImageAccept;
import model.Staffs;

@SuppressWarnings("serial")
public class StaffDialog extends JDialog {
	private AdminFrameView adminFrameView;
	private Staffs staff;
	private byte[] staffIcon;

	private JTextField txtStaffId;
	private JCheckBox cbActive;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JFormattedTextField ftxtBirthDay;
	private JTextField txtPhone;
	private JRadioButton rdbFemale;
	private JRadioButton rdbMale;
	private JComboBox<Integer> cbbStoreId;
	private JComboBox<Integer> cbbManagerId;
	private JButton btnOpen;
	private JButton btnBack;
	private JButton btnSave;
	private JLabel lbIcon;

	public StaffDialog(AdminFrameView adminFrameView, Staffs staff) {
		super(adminFrameView, true);
		this.adminFrameView = adminFrameView;
		this.staff = staff;
		initComponent();
		setEvent();
		loadDataTocombobox();
		if (staff != null) {
			initInformation();
		}
		pack();
		setTitle("Staff View");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initComponent() {
		// component
		// label
		Font font = new Font("Arial", Font.PLAIN, 16);
		JLabel lbStaffId = new JLabel("Staff ID");
		lbStaffId.setFont(font);
		JLabel lbStoreId = new JLabel("Store ID");
		lbStoreId.setFont(font);
		JLabel lbActive = new JLabel("Active");
		lbActive.setFont(font);
		JLabel lbManagerId = new JLabel("Manager ID");
		lbManagerId.setFont(font);
		JLabel lbFirstName = new JLabel("FirstName");
		lbFirstName.setFont(font);
		JLabel lbLastName = new JLabel("LastName");
		lbLastName.setFont(font);
		JLabel lbSex = new JLabel("Sex");
		lbSex.setFont(font);
		JLabel lbBirthDay = new JLabel("BirthDay");
		lbBirthDay.setFont(font);
		JLabel lbPhone = new JLabel("Phone");
		lbPhone.setFont(font);
		// textfield
		txtStaffId = new JTextField(10);
		txtStaffId.setFont(font);
		txtStaffId.setEnabled(false);
		;
		txtFirstName = new JTextField(10);
		txtFirstName.setFont(font);
		txtLastName = new JTextField(14);
		txtLastName.setFont(font);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		ftxtBirthDay = new JFormattedTextField(sdf);
		ftxtBirthDay.setToolTipText("DD-MM-YYYY");
		ftxtBirthDay.setFont(font);
		txtPhone = new JTextField(10);
		txtPhone.setFont(font);
		// radiobutton
		rdbFemale = new JRadioButton("Female");
		rdbFemale.setFont(font);
		rdbMale = new JRadioButton("Male");
		rdbMale.setFont(font);
		// combobox
		cbbStoreId = new JComboBox<Integer>();
		cbbStoreId.setFont(font);
		cbbManagerId = new JComboBox<Integer>();
		cbbManagerId.setFont(font);
		// checkbox
		cbActive = new JCheckBox();
		cbActive.setFont(font);
		// buttons
		btnOpen = new JButton("Open", new ImageIcon(getClass().getResource("/icon/open-file-icon16.png")));
		btnOpen.setFont(font);
		btnBack = new JButton("Back", new ImageIcon(getClass().getResource("/icon/Go-back-icon16.png")));
		btnBack.setFont(font);
		btnSave = new JButton("Save", new ImageIcon(getClass().getResource("/icon/Save-icon16.png")));
		btnSave.setFont(font);
		// lable icon
		lbIcon = new JLabel(new ImageIcon(getClass().getResource("/icon/Preppy-icon72.png")));
		lbIcon.setBorder(new LineBorder(Color.CYAN));
		lbIcon.setPreferredSize(new Dimension(100, 100));

		// component status

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbMale);
		buttonGroup.add(rdbFemale);
		rdbMale.setSelected(true);

		// layout
		// panelEmployee
		JPanel panelEmployee = new JPanel();
		panelEmployee.setBorder(new TitledBorder("Employee Information"));
		panelEmployee.setLayout(new GridBagLayout());
		GridBagConstraints gbcEmployee = new GridBagConstraints();

		gbcEmployee.insets = new Insets(5, 5, 5, 5);
		gbcEmployee.weighty = 1;

		gbcEmployee.gridx = 0;
		gbcEmployee.gridy = 0;
		gbcEmployee.anchor = GridBagConstraints.WEST;
		panelEmployee.add(lbStaffId, gbcEmployee);

		gbcEmployee.gridx = 0;
		gbcEmployee.gridy = 1;
		panelEmployee.add(lbStoreId, gbcEmployee);

		gbcEmployee.gridx = 0;
		gbcEmployee.gridy = 2;
		panelEmployee.add(lbActive, gbcEmployee);

		gbcEmployee.gridx = 0;
		gbcEmployee.gridy = 3;
		panelEmployee.add(lbManagerId, gbcEmployee);

		gbcEmployee.gridx = 1;
		gbcEmployee.gridy = 0;
		gbcEmployee.fill = GridBagConstraints.HORIZONTAL;
		gbcEmployee.weightx = 1;
		panelEmployee.add(txtStaffId, gbcEmployee);

		gbcEmployee.gridx = 1;
		gbcEmployee.gridy = 1;
		panelEmployee.add(cbbStoreId, gbcEmployee);

		gbcEmployee.gridx = 1;
		gbcEmployee.gridy = 2;
		panelEmployee.add(cbActive, gbcEmployee);

		gbcEmployee.gridx = 1;
		gbcEmployee.gridy = 3;
		panelEmployee.add(cbbManagerId, gbcEmployee);

		gbcEmployee.gridx = 3;
		gbcEmployee.gridy = 0;
		gbcEmployee.gridheight = 3;
		gbcEmployee.fill = GridBagConstraints.NONE;
		gbcEmployee.anchor = GridBagConstraints.CENTER;
		panelEmployee.add(lbIcon, gbcEmployee);

		gbcEmployee.gridy = 3;
		panelEmployee.add(btnOpen, gbcEmployee);

		// panelPersonal
		JPanel panelPersonal = new JPanel();
		panelPersonal.setBorder(new TitledBorder("Personal Information"));
		panelPersonal.setLayout(new GridBagLayout());

		GridBagConstraints gbcInformation = new GridBagConstraints();
		gbcInformation.insets = new Insets(5, 5, 5, 5);
		gbcInformation.weighty = 1;

		gbcInformation.gridx = 0;
		gbcInformation.gridy = 0;
		gbcInformation.anchor = GridBagConstraints.WEST;
		panelPersonal.add(lbFirstName, gbcInformation);

		gbcInformation.gridx = 0;
		gbcInformation.gridy = 1;
		panelPersonal.add(lbSex, gbcInformation);

		gbcInformation.gridx = 0;
		gbcInformation.gridy = 2;
		panelPersonal.add(lbPhone, gbcInformation);

		gbcInformation.gridx = 3;
		gbcInformation.gridy = 0;
		panelPersonal.add(lbLastName, gbcInformation);

		gbcInformation.gridx = 3;
		gbcInformation.gridy = 1;
		panelPersonal.add(lbBirthDay, gbcInformation);

		gbcInformation.gridx = 1;
		gbcInformation.gridy = 0;
		gbcInformation.fill = GridBagConstraints.HORIZONTAL;
		gbcInformation.weightx = 1;
		gbcInformation.gridwidth = 2;
		panelPersonal.add(txtFirstName, gbcInformation);

		gbcInformation.gridx = 1;
		gbcInformation.gridy = 2;
		panelPersonal.add(txtPhone, gbcInformation);

		gbcInformation.gridx = 1;
		gbcInformation.gridy = 1;
		gbcInformation.gridwidth = 1;
		panelPersonal.add(rdbMale, gbcInformation);

		gbcInformation.gridx = 2;
		gbcInformation.gridy = 1;
		panelPersonal.add(rdbFemale, gbcInformation);

		gbcInformation.gridx = 4;
		gbcInformation.gridy = 0;
		panelPersonal.add(txtLastName, gbcInformation);

		gbcInformation.gridx = 4;
		gbcInformation.gridy = 1;
		panelPersonal.add(ftxtBirthDay, gbcInformation);

		JPanel pannelButton = new JPanel();
		pannelButton.setLayout(new GridBagLayout());

		GridBagConstraints gbcButton = new GridBagConstraints();

		gbcButton.weighty = 0;
		gbcButton.weightx = 1;

		gbcButton.gridx = 0;
		gbcButton.gridy = 0;
		gbcButton.anchor = GridBagConstraints.WEST;
		pannelButton.add(btnBack, gbcButton);

		gbcButton.gridx++;
		gbcButton.anchor = GridBagConstraints.EAST;
		pannelButton.add(btnSave, gbcButton);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		gbc.weightx = 1;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(panelEmployee, gbc);

		gbc.gridy++;
		add(panelPersonal, gbc);

		gbc.gridy++;
		gbc.weighty = 0;
		add(pannelButton, gbc);
	}

	private void setEvent() {
		StaffsDialogController staffsDialogController = new StaffsDialogController(this);
		txtFirstName.addFocusListener(staffsDialogController);
		txtLastName.addFocusListener(staffsDialogController);
		txtPhone.addFocusListener(staffsDialogController);
		btnBack.addActionListener(staffsDialogController);
		btnOpen.addActionListener(staffsDialogController);
		btnSave.addActionListener(staffsDialogController);
	}

	private void initInformation() {
		if (staff.getStaffIcon() != null) {
			staffIcon = staff.getStaffIcon();
			try {
				Image img = ImageAccept.createImageFromByteArray(staffIcon, ".jpg");
				Image resize = ImageAccept.resize(img, 100, 100);
				ImageIcon icon = new ImageIcon(resize);
				lbIcon.setIcon(icon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		txtStaffId.setText(String.valueOf(staff.getStaffId()));
		if (staff.getActive()) {
			cbActive.setSelected(true);
		} else {
			cbActive.setSelected(false);
		}
		cbbStoreId.setSelectedItem(staff.getStoreId());
		cbbManagerId.setSelectedItem(staff.getManagerId());
		txtFirstName.setText(staff.getFirstName());
		txtLastName.setText(staff.getLastName());
		if (staff.getSex()) {
			rdbMale.setSelected(true);
		} else {
			rdbFemale.setSelected(true);
		}
		if (staff.getBirthDay() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateFormat = sdf.format(staff.getBirthDay());
			ftxtBirthDay.setText(dateFormat);
		}
		txtPhone.setText(staff.getPhone());
	}

	private void loadDataTocombobox() {
		StoresDao storesDao = new StoresDao();
		ArrayList<Integer> result = storesDao.allId();
		result.add(0, null);
		for (Integer integer : result) {
			cbbStoreId.addItem(integer);
		}
		StaffsDao staffsDao = new StaffsDao();
		result = staffsDao.allId();
		result.add(0, null);
		if (staff != null) {
			result.remove(staff.getStaffId());
		}
		for (Integer integer : result) {
			cbbManagerId.addItem(integer);
		}
	}

	public byte[] getStaffIcon() {
		return staffIcon;
	}

	public void setStaffIcon(byte[] staffIcon) {
		this.staffIcon = staffIcon;
	}

	public AdminFrameView getAdminFrameView() {
		return adminFrameView;
	}

	public Staffs getStaff() {
		return staff;
	}

	public JTextField getTxtStaffId() {
		return txtStaffId;
	}

	public JCheckBox getCbActive() {
		return cbActive;
	}

	public JTextField getTxtFirstName() {
		return txtFirstName;
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

	public JRadioButton getRdbFemale() {
		return rdbFemale;
	}

	public JRadioButton getRdbMale() {
		return rdbMale;
	}

	public JComboBox<Integer> getCbbStoreId() {
		return cbbStoreId;
	}

	public JComboBox<Integer> getCbbManagerId() {
		return cbbManagerId;
	}

	public JButton getBtnOpen() {
		return btnOpen;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JLabel getLbIcon() {
		return lbIcon;
	}

	public static void main(String[] args) {
		new StaffDialog(null, null);
	}
}