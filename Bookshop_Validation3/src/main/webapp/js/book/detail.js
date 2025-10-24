window.addEventListener("load", () => {
    document.getElementById("add_review").addEventListener("click", e=> {
        const review = document.getElementById("review").value;
        const item = {
            bookCode,
            contents: review,
        };

        fetch(`../review`, {
            body: JSON.stringify(item),
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(resp => {
            if(resp.ok){
                return "등록되었습니다.";
            }
        }).catch(e => alert(e));
    })
})