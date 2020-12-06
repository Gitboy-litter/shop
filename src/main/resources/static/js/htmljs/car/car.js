let vm = new Vue({
    el: "#app",
    data() {
        return {
            productlist: {}
        }
    },
    methods: {
        mycar() {
            axios({
                url: `shoppingCat/mycar`
            }).then(response => {
                this.productlist = response.data.data;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        toDetail: function (product) {
            sessionStorage.setItem("product", JSON.stringify(product))
            location.href = "goodsDetail.html";
        },
        payment: function (id) {
            axios({
                url: `shoppingCat/payment/${id}`,
                method: "put"
            }).then(response => {
                location.href="/waitPay.html"
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        remove: function (id) {
            layer.confirm('真的要删除？', {
                btn: ['是的', '不要'] //按钮
            }, () => {
                axios({
                    url: `shoppingCat/delete/${id}`,
                    method: "delete"
                }).then(response => {
                    layer.msg("操作成功")
                    this.mycar();
                }).catch(error => {
                    layer.msg(error.message)
                })
            }, function () {
            });
        }
    },
    created: function () {
        this.mycar();
    }
});
