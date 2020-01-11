package com.bw.forwardweek3.model.bean;

import java.util.List;

public class CartBean {

    /**
     * result : [{"categoryName":"女鞋","shoppingCartList":[{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","count":3,"pic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg","price":278},{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","count":4,"pic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78}]},{"categoryName":"美妆护肤","shoppingCartList":[{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39},{"commodityId":5,"commodityName":"双头两用修容笔","count":10,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39}]}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    //商家数组
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    //商家的bean类
    public static class ResultBean {
        /**
         * categoryName : 女鞋
         * shoppingCartList : [{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","count":3,"pic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg","price":278},{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","count":4,"pic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78}]
         */

        private String categoryName;

        //所有的商品集合
        private List<ShoppingCartListBean> shoppingCartList;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<ShoppingCartListBean> getShoppingCartList() {
            return shoppingCartList;
        }

        public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
            this.shoppingCartList = shoppingCartList;
        }

        //商品的bean类
        public static class ShoppingCartListBean {
            /**
             * commodityId : 29
             * commodityName : 秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲
             * count : 3
             * pic : http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg
             * price : 278
             */

            private int commodityId;
            private String commodityName;
            private int count;
            private String pic;
            private double price;
            //商品的选中状态
            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
        }
    }
}
