package com.whitelabel.app;

/**
 * Created by img on 2017/11/23.
 */

public class Const {
    //recyclerview diff data type
    public final static int HEADER = 1;
    public final static int TITLE = 2;
    public final static int ITEM = 3;
    public final static int FOOTER = 4;

    public static final int TYPE_TREE_LEVEL_0 = 0;
    public static final int TYPE_TREE_LEVEL_1 = 1;

    //banner 3:8
    public static final double BANNER_PIC_HEIGHT_THAN_WIDTH = 180.0/480;
    public static final int NORMAL_BANNER_DELAY_TIME=3000;

    public interface GA{
        public final static String HOME_SCREEN = " Category Landing Screen";
        public final static String CATEGORY_ALL_LIST_SCREEN = "Category list";
        public final static String ADD_ADDRESS_SCREEN = "Add Address Screen";
        public final static String EDIT_ADDRESS_SCREEN = "Edit Address Screen";
        public final static String ACCOUNT_WISHLIST_SCREEN = "My Wishlist Screen";
        public final static String ADDRESS_BOOK_SCREEN = "My Address Book Screen";
        public final static String SIGN_UP_SCREEN = "Sign Up Screen";
        public final static String SIGN_UP_SUCCEED_SCREEN = "Sign Up Succeed Screen";
        public final static String SLIDE_MENU_SCREEN = "Side Menu";
        public final static String FORGOT_PASSWORD_SCREEN = "Forgot Password Screen";
        public final static String SEARCH_LIST_SCREEN = "Search List Screen";

        public final static String HOME_BANNER_EVENT = "Banner";
        public final static String ORDER_ADD_TO_CART_EVENT = "Add to Cart by icon";
        public final static String ORDER_CHOOSE_PRODUCT_EVENT = "Choose Product";
        public final static String ORDER_MODIFY_QTY_EVENT = "Modify qty";
        public final static String ORDER_ADD_EVENT = "Add";
        public final static String ORDER_SUBTRACT_EVENT = "Subtract";
        public final static String SIGN_UP_EVENT = "Sign Up";

        public final static String ORDER_REORDER_CATEGORY = "Reorder";

//        GA - event
        public final static String EVENT_SETTINGS = "Settings";
        public final static String EVENT_SETTINGS_NEWSLETTERS_SELECT = "Select";
        public final static String EVENT_SETTINGS_NEWSLETTERS_UNSELECT = "Unselect";
        public final static String EVENT_SETTINGS_RATE_APP = "Rate App";
        public final static String EVENT_REORDER_ORDERLIST = "OrderList";
        public final static String EVENT_REORDER_ORDERDETAIL = "OrderDetail";


    }

}
