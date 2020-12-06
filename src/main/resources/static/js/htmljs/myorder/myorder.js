let vm = new Vue({
    el: "#app",
    data() {
        return {
            productlist: {},
            collectlist: {},
            isAppriselist: {},
            pagetotle: 1,
            pagenum: 1,
            user: "",
            shopcount: "",
            password: {
                oldpassword: "",
                newpassword: "",
                confirpassword: "",
            }
        }
    },
    methods: {
        mycollect: function (pagenum = 1) {
            if (pagenum > this.pagetotle || pagenum <= 0) {
                return;
            }
            axios({
                url: `product/mycollect/${pagenum}`
            }).then(response => {
                this.collectlist = response.data.data.rows;
                this.pagenum = pagenum;
                this.pagetotle = response.data.data.totalPage;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        mywaitpay() {
            axios({
                url: `shoppingCat/myorder`
            }).then(response => {
                this.productlist = response.data.data;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        findshopcount() {
            axios({
                url: `shoppingCat/findcount`
            }).then(response => {
                this.shopcount = response.data.data;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        isapprise() {
            axios({
                url: `shoppingCat/isapprise`
            }).then(response => {
                this.isAppriselist = response.data.data;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        toDetail: function (product) {
            sessionStorage.setItem("product", JSON.stringify(product));
            location.href = "goodsDetail.html";
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
                    this.mywaitpay();
                }).catch(error => {
                    layer.msg(error.message)
                })
            }, function () {
            });
        },
        confirproduct: function (id) {
            axios({
                url: `shoppingCat/confirproduct/${id}`,
                method: "put"
            }).then(response => {
                this.mywaitpay();
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        findUser: function () {
            axios({
                url: `user/findUserAndAddresss`,
            }).then(response => {
                this.user = response.data.data;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        changesex: function () {
            if (this.user.sex == 1) {
                this.user.sex = 0;
            } else {
                this.user.sex = 1
            }
        },
        save() {
            axios({
                url: `user/save`,
                method: "put",
                data: this.user
            }).then(response => {
                layer.msg("修改成功", {icon: 1})
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        checkOldpassword() {
            if (this.password.oldpassword != this.user.password) {
                layer.msg("旧密码不正确")
                this.password.oldpassword = "";
            }
        },
        checktwoPassword() {
            if (this.password.confirpassword != this.password.newpassword) {
                layer.msg("两次密码不一致")
                this.password.confirpassword = "";
            }
        },
        checkONPassword() {
            if (this.password.oldpassword == this.password.newpassword) {
                layer.msg("新旧密码不可以一样")
                this.password.newpassword = "";
            }
        },
        updatepassword() {
            let flag = true;
            $("input password").each(function () {
                if ($(this).val() === "") {
                    layer.msg("信息不对", {icon: 1})
                    flag = false;
                }
            });
            if (flag) {
                axios({
                    url: `user/updatepassword/${this.password.newpassword}`,
                    method: "put",
                }).then(response => {
                    layer.msg("修改成功", {icon: 1})
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        }
    },
    created: function () {
        this.findshopcount();
        this.findUser();
        // this.mycollect();
        this.mywaitpay();
    }
});
