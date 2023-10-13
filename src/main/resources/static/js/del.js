const delAll = document.querySelectorAll('#trDiv .del');

[].forEach.call(delAll, function(col){
    col.addEventListener("click" , click , false );
});


//카트 페이지에서 상품삭제 버튼 클릭시 단건 delete 발생
function click(e){
        console.log(this)
        let cartId = document.getElementById('cartId').value;
        let index = this.getAttribute("id")
        let productId = this.getAttribute("prdId")

        fetch(`/api/cart/${cartId}/${productId}`,{
            method :'DELETE',
            headers : {"Content-Type": "application/json",},
        })
            .then((response) => {
                if(response.ok){return response.json()}
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            }).then(data => {
            document.querySelector("#tBody").deleteRow(index)
        })
            .catch(error => {
                alert('삭제실패! 잠시 후 시도해주세요.');
            })

}
