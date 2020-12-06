let vm = new Vue({
    el: "#app",
    data() {
        return {
            username: "",
            email: "",
            password: "",
            code: "",
        }
    },
    methods: {
        checkEmail: function () {
            if ($("#email").val() === "") {
                $("#email").addClass("input-error")
                layer.msg("邮箱不可以为空", {icon: 1})
            } else {
                axios({
                    url: `index/checkEmail/${this.user.email}`,
                }).then(response => {
                    if (!response.data.flag) {
                        layer.msg("邮箱没有注册", {icon: 1})
                        $("#email").addClass("input-error")
                    } else {
                        $("#email").removeClass("input-error")
                    }
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        },
        sendEmail: function () {
            if ($("#email").val() === "") {
                layer.msg("邮箱不能为空", {icon: 1})
            } else {
                axios({
                    url: "user/sendEmail",
                    method: "get",
                    params: {
                        email: this.email
                    }
                }).then(response => {
                    layer.msg(response.data.message, {icon: 1})
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        },
        save: function () {
            let flag = true;
            $("input").each(function () {
                if ($(this).val() === "") {
                    layer.msg("信息有误", {icon: 1});
                    flag = false;
                }
            });
            if (flag) {
                axios({
                    url: "user/forget",
                    method: "put",
                    data: {
                        email: this.email, password: this.password, code: this.code, username: this.username
                    }
                }).then(response => {
                    if (response.data.flag) {
                        location.href = "login.html"
                    } else {
                        layer.msg(response.data.message, {icon: 1})
                    }
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        }
    }
})