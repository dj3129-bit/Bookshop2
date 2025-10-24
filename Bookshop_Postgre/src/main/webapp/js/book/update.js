const appendFile = e => {
    const ul = document.getElementById("files");

    const li = document.createElement("li");

    const input = document.createElement("input");
    input.setAttribute("type", "file");
    input.setAttribute("name", "uploadFile");
    input.classList.add("form-control", "form-control-sm");
        
    li.append(input);
    ul.append(li);
};

window.addEventListener("load", () => {
	document.getElementById("image_add").addEventListener("click", appendFile);
    document.querySelectorAll(".delete").forEach(item => {
        item.addEventListener("click", e => {
            const { code } = e.target.dataset;

            fetch(`../attach/delete/${code}`)
            .then(resp => {
                if(resp.ok){
                    alert("삭제되었습니다");
                    
                    e.target.closest("li").remove();
                }
            }).catch(err => alert(err));
        });
    });
});