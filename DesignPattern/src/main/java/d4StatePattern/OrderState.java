package d4StatePattern;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public enum OrderState {
    /**
     * 未支付
     */
    UnPayed,
    /**
     * 已支付
     */
    Payed,
    /**
     * 已确认
     */
    Confirmed,
    /**
     * 已退款
     */
    Refund;
}
