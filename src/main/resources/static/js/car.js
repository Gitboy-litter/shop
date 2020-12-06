$(function () {
    $(".goods-add").on("click", function () {
        var num = parseInt($(this).parent().siblings(".goods-num").val());
        $(this).parent().siblings(".goods-num").val(++num);

        var price = parseFloat($(".single-price").text().slice(1));
        $(this).parents(".goods-counts").siblings(".goods-money-count").children("span").text("￥" + (price * num).toFixed(2))
        getSum();
    });
    //数量-
    $(".goods-sub").on("click", function () {
        var num = parseInt($(this).parent().siblings(".goods-num").val());
        if (--num <= 0) {
            num = 1;
        }
        $(this).parent().siblings(".goods-num").val(num);
        var price = parseFloat($(".single-price").text().slice(1));
        $(this).parents(".goods-counts").siblings(".goods-money-count").children("span").text("￥" + (price * num).toFixed(2))
        getSum();
    });
    getSum();

    //全部商品 金额
    function getSum() {
        var moneyList = $(".goods-totalMoney");
        var money = 0;
        $.each(moneyList, function (index, item) {
            money += parseFloat($(item).text().slice(1));
        });
        $(".goodsList-totalMoney").val("￥" + money.toFixed(2))
    }
})