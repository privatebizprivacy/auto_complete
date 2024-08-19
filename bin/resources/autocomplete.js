$(document).ready(function(){

    const storageKey = 'autocompleteData'; // localStorage에 저장할 키 이름

    // 로컬 저장소에서 데이터를 가져오는 함수
    function getStoredData() {
        const data = localStorage.getItem(storageKey);
        return data ? JSON.parse(data) : [];
    }

    // 로컬 저장소에 데이터를 저장하는 함수
    function setStoredData(data) {
        localStorage.setItem(storageKey, JSON.stringify(data));
    }

    // 입력 필드의 내용이 변경될 때마다 호출
    $('#search-input').on('input', function(){
        const query = $(this).val();
        const storedData = getStoredData();

        // 입력값이 비어있는 경우 리스트를 숨김
        if(query === '') {
            $('#autocomplete-list').empty();
            return;
        }

        // 저장된 데이터에서 입력값과 일치하는 항목을 필터링
        const filteredData = storedData.filter(item => item.toLowerCase().includes(query.toLowerCase()));

        // 필터링된 데이터를 리스트에 표시
        const autocompleteList = $('#autocomplete-list');

        if(filteredData.length > 0) {
            autocompleteList.empty(); // 기존의 리스트 제거

            filteredData.forEach(function(item) {
                autocompleteList.append('<div>' + item + '</div>');
            });
        }

        // 리스트 항목을 클릭하면 입력 필드에 값 반영
        $('#autocomplete-list div').on('click', function() {
            const selectedValue = $(this).text();
            $('#search-input').val(selectedValue);
            autocompleteList.empty(); // 리스트 초기화

            // 선택된 검색어를 저장소에 추가 (중복 제거)
            if (!storedData.includes(selectedValue)) {
                storedData.push(selectedValue);
                setStoredData(storedData);
            }
        });
    });

    // 입력 필드가 포커스를 잃으면 리스트를 숨김
    $(document).click(function(e) {
        if (!$(e.target).closest('#search-input, #autocomplete-list').length) {
            $('#autocomplete-list').empty();
        }
    });

    $.ajax({
        url: '/data.dat', // 요청을 보낼 URL
        method: 'POST', // HTTP 요청 방식 ('GET', 'POST', 'PUT', 'DELETE' 등)
        dataType: 'json', // 서버로부터 받을 데이터 형식 ('json', 'xml', 'html', 'text' 등)
        success: function(response) {
            // 요청이 성공했을 때 실행할 콜백 함수
            console.log(response);
            setStoredData(response.data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // 요청이 실패했을 때 실행할 콜백 함수
            console.error("Error: Fail");
        }
    });
});