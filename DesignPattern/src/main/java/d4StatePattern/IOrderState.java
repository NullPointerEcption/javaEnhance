package d4StatePattern;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public interface IOrderState {
        void updateUI();
        void pay();
        void refund();
        void confirm();
}
