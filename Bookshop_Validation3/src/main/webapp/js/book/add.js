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
});