const deliveryBtn = document.getElementById("deliveryBtn");

//카트 페이지에서 상품삭제 버튼 클릭시 단건 delete 발생
if(deliveryBtn){
    deliveryBtn.addEventListener('click',event =>{
        let orderId = document.getElementById('orderId').value;
        console.log(`orderId: ${orderId}`)
        fetch('/api/delivery',{
            method :'POST',
            headers : {"Content-Type": "application/json",},
            body : JSON.stringify({
                orderId: document.getElementById('orderId').value, //키:밸류

                //content: document.getElementById("editContent").value,
                // member:document.getElementById("author").value

            })
        })
            .then((response) => {
                if(response.ok){
                    return response.json()
                }
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            })
            .then(data => {
            location.href=`/myPage`;
        })
            .catch(error => {
                alert('잠시 후 시도해주세요.');
            })
    })
}


