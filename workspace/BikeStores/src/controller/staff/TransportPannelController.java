package controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import dao.StocksDao;
import dao.TransportTransaction;
import model.Orders;
import view.staff.CustomerDialog;
import view.staff.OrderDialogCustomer;
import view.staff.TransportPanel;

public class TransportPannelController implements ActionListener {
	private TransportPanel transportPannel;

	public TransportPannelController(TransportPanel transportPannel) {
		this.transportPannel = transportPannel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == transportPannel.getBtnTransport()) {
			try {
				new CustomerDialog(transportPannel.getStaffFrameView());
				if (transportPannel.getCustomer() != null) {
					// lay du lieu tu table
					// loc cac tich chon de mua => luu vao mang data
					int row = transportPannel.getTable().getRowCount();
					ArrayList<Object[]> data = new ArrayList<Object[]>();
					for (int i = 0; i < row; i++) {
						if ((Boolean) transportPannel.getTable().getValueAt(i, 0)) {
							data.add(new Object[] {
									Integer.valueOf(transportPannel.getTable().getValueAt(i, 1).toString()),
									Integer.valueOf(transportPannel.getTable().getValueAt(i, 2).toString()),
									Integer.valueOf(transportPannel.getTable().getValueAt(i, 4).toString()),
									new BigDecimal(transportPannel.getTable().getValueAt(i, 5).toString()) });
						}
					}
					// sap xep mang data theo storeId
					Collections.sort(data, new Comparator<Object[]>() {
						@Override
						public int compare(Object[] o1, Object[] o2) {
							Integer buy1 = (Integer) o1[0];
							Integer buy2 = (Integer) o2[0];
							return buy1.compareTo(buy2);
						}
					});
					TransportTransaction transportTransaction = new TransportTransaction();
					ArrayList<Orders> orders = transportTransaction.Buy(data, transportPannel.getCustomer());
					// load lại shoppanel, historypannel
					transportPannel.getStaffFrameView().getHistoryPanel().loadDataToTable();
					transportPannel.getStaffFrameView().getShopPanel().getCardLayout()
							.show(transportPannel.getStaffFrameView().getShopPanel(), "1");
					StocksDao stocksDao = new StocksDao();
					transportPannel.getStaffFrameView().getShopPanel()
							.setObjects(stocksDao.selectAllProductRemaingAndSoldFromStock());
					transportPannel.getStaffFrameView().getShopPanel().createProductPanel();
					transportPannel.getStaffFrameView().getShopPanel().loadProduct();
					// xoa cac row da mua
					boolean[] check = new boolean[row];
					for (int i = 0; i < row; i++) {
						check[i] = (Boolean) transportPannel.getTable().getValueAt(i, 0);
					}
					for (int i = row - 1; i >= 0; i--) {
						if (check[i])
							transportPannel.getTblModel().removeRow(i);
					}
					// tạo các order và update các stock
					for (Orders order : orders) {
						new OrderDialogCustomer(transportPannel.getStaffFrameView(), order);
					}
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(transportPannel, "Faild!");
			}
		}
		if (e.getSource() == transportPannel.getBtnDelete()) {
			if (transportPannel.getTable().getSelectedRow() != -1) {
				transportPannel.getTblModel().removeRow(transportPannel.getTable().getSelectedRow());
			}
		}
	}
}
