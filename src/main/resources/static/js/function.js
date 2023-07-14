// AJAX 요청을 비동기로 처리하고 콜백 함수로 처리 결과를 전달하는 함수
function callApi(uri, method, params, callback) {

    $.ajax({
        url: uri,
        type: method,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: (params) ? JSON.stringify(params) : {},
        success: function(response) {
            if(callback == null) return false;
            callback(response);
        },
        error: function(request, status, error) {
            console.log(error);
        }
    });
}

/**
 * 데이터 조회
 * @param uri - API Request URI
 * @param params - Parameters
 * @returns json - 결과 데이터
 */
function getJson(uri, params, callback) {

    let json = {}

    $.ajax({
        url : uri,
        type : 'get',
        dataType : 'json',
        data : params,
        async : false,
        success : function (response) {
            callback(response);
        },
        error : function (request, status, error) {
            console.log(error)
        }
    });
}
