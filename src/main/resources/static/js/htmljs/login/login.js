let vm = new Vue({
    el: "#app",
    data() {
        return {
            check: "",
            username: "",
            password: "",
            code: "",
            render: "user/render"
        }
    },
    methods: {
        refresh: function () {
            this.render = "user/render?" + new Date()
        },
        login: function () {
            let flag = true;
            $("input").each(function () {
                if ($(this).val() === "") {
                    flag = false;
                    layer.msg("信息有误", {icon: 1})
                }
            });
            if (flag) {
                axios({
                    url: "user/login",
                    method: "post",
                    data: {
                        username: this.username, password: this.password, code: this.code, check: this.check
                    }
                }).then(response => {
                    if (response.data.flag) {
                        let data = response.data.data;
                        localStorage.setItem("loginUser", JSON.stringify(data))
                        location.href = "home.html"
                    } else {
                        this.refresh()
                        layer.msg(response.data.message, {icon: 1})
                    }
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        },
    }
})