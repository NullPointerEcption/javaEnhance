package d4StatePattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public class App1 extends JFrame {

    private JButton btnPay;
    private JButton btnRefund;
    private JButton btnConfirm;
    private JLabel labelState;
    private IOrderState state;//订单的状态

    public App1() throws HeadlessException {
        super("测试订单支付界面");
        setSize(1000,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel contentPane=new JPanel();
        setContentPane(contentPane);
        btnPay=new JButton("付款");
        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App1.this.state.pay();
            }
        });
        btnRefund=new JButton("退款");
        btnRefund.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App1.this.state.refund();
            }
        });
        btnConfirm=new JButton("确认收货");
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App1.this.state.confirm();
            }
        });
        labelState = new JLabel();
        GridLayout gird=new GridLayout(7,5);
        contentPane.setLayout(gird);

        contentPane.add(btnPay);
        contentPane.add(btnRefund);
        contentPane.add(btnConfirm);
        contentPane.add(labelState);

        updateInvoiceState(new UnPayedState());
    }

    /**
     * 更改订单的状态
     * @param stat
     */
    public void updateInvoiceState(IOrderState stat)
    {
        this.state = stat;
        this.state.updateUI();
    }

    //-----------------------------------------执行MAIN方法-------------------------------------------------------------------
    public static void main(String[] args) {
        App1 app=new App1();
        app.pack();
    }
    //-----------------------------------------执行MAIN方法-------------------------------------------------------------------


    class UnPayedState implements IOrderState
    {

        @Override
        public void updateUI() {
            App1.this.btnConfirm.setEnabled(false);
            App1.this.btnPay.setEnabled(true);
            App1.this.btnRefund.setEnabled(false);
            App1.this.labelState.setText("未支付");
        }

        @Override
        public void pay() {
            App1.this.updateInvoiceState(new PayedState());
        }

        @Override
        public void refund() {
            throw new RuntimeException("未付款无法退款");
        }

        @Override
        public void confirm() {
            throw new RuntimeException("未付款无法确认");
        }

    }

    class PayedState implements IOrderState
    {

        @Override
        public void updateUI() {
            App1.this.btnConfirm.setEnabled(true);
            App1.this.btnPay.setEnabled(false);
            App1.this.btnRefund.setEnabled(true);
            App1.this.labelState.setText("已支付");
        }

        @Override
        public void pay() {
            throw new RuntimeException("已经支付，不需要重复支付");
        }

        @Override
        public void refund() {
            App1.this.updateInvoiceState(new RefundedState());
        }

        @Override
        public void confirm() {
            App1.this.updateInvoiceState(new ConfirmedState());
        }

    }
    class RefundedState implements IOrderState
    {

        @Override
        public void updateUI() {
            App1.this.btnConfirm.setEnabled(false);
            App1.this.btnPay.setEnabled(false);
            App1.this.btnRefund.setEnabled(false);
            App1.this.labelState.setText("已退款");

        }

        @Override
        public void pay() {
            throw new RuntimeException("已经退款，无法支付");
        }

        @Override
        public void refund() {
            throw new RuntimeException("已经退款，不能重复退款");
        }

        @Override
        public void confirm() {
            throw new RuntimeException("已经退款，不能确认");
        }

    }

    class ConfirmedState implements IOrderState
    {
        @Override
        public void updateUI() {
            App1.this.btnConfirm.setEnabled(false);
            App1.this.btnPay.setEnabled(false);
            App1.this.btnRefund.setEnabled(false);
            App1.this.labelState.setText("确认收货");

        }

        @Override
        public void pay() {
            throw new RuntimeException("已经确认，不能重复支付");
        }

        @Override
        public void refund() {
            throw new RuntimeException("已经确认，不能退款");

        }

        @Override
        public void confirm() {
            throw new RuntimeException("已经确认，不能重复确认");
        }

    }
}
