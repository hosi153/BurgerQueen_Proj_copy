const saveBtn = document.getElementById('saveBtn');

if(saveBtn){
    saveBtn.addEventListener('click',event =>{
        // let cartId = document.getElementById('cartId').value;
        let memberEmail = document.getElementById("memberEmail").value;
        fetch(`/api/member/${memberEmail}`, {
            method: 'PATCH',
            headers: {"Content-Type": "application/json",},
            body: JSON.stringify({
                address1: document.getElementById("editAddr1").value,
                address2: document.getElementById("editAddr2").value,
                address3: document.getElementById("editAddr3").value,
                phone: document.getElementById("editPhone").value
            })
        })
            .then((response) => {
                if (response.ok) {
                    return response.json()
                }
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            }).then(data => {
            // sessionStorage.setItem('cart', cartListAll);
                Swal.fire('','변경되었습니다.','success')
                    .then(function(){
                    location.href = `/myPage`;
                })

           })
            .catch(error => {
                // certain('warning', '잠시 후 시도해주세요.')
                Swal.fire('','잠시 후 시도해주세요..','warning')
                    .then(function(){
                        location.href = `/myPage`;
                    })
            })

    })
}

