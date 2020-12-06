let vm = new Vue({
    el: "#app",
    data() {
        return {
            user: {},
            confirpassword: ""
        }
    },
    methods: {
        checkEmail: function () {
            if ($("#email").val() === "") {
                layer.msg("请输入邮箱", {icon: 1})
            } else {
                axios({
                    url: `user/checkEmail/${this.user.email}`,
                }).then(response => {
                    if (!response.data.flag) {
                        layer.msg(response.data.message, {icon: 1})
                        $("#email").val("")
                    }
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        },
        checkUsername: function () {
            if ($("#username").val() === "") {
                layer.msg("请输入用户", {icon: 1})
            } else {
                axios({
                    url: `user/checkUsername/${this.user.username}`,
                }).then(response => {
                    if (!response.data.flag) {
                        layer.msg(response.data.message, {icon: 1})
                        $("#username").val("")
                    }
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        },
        checkPassword: function () {
            if (this.confirpassword === "" || this.user.password === "") {
                layer.msg("不能为空", {icon: 1})
            }
            if (this.confirpassword !== this.user.password) {
                layer.msg("两次输入密码不一致", {icon: 1})
                this.confirpassword = ""
            }
        },
        register: function () {
            let flag = true;
            $("input").each(function () {
                if ($(this).val() === "") {
                    layer.msg("信息不对", {icon: 1})
                    flag = false;
                }
            });
            if (flag) {
                axios({
                    url: "user/register",
                    method: "post",
                    data: this.user
                }).then(response => {
                    if (response.data.flag) {
                        location.href = "/login.html"
                    } else {
                        layer.msg(response.data.message, {icon: 1})
                    }
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        },
    }
})