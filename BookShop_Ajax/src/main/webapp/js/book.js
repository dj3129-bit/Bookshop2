const movePage = pager => {
    fetch("/book/list", {
    	method: "POST",
    	headers: {
    		"Content-Type": "application/json"
    	},
        body: JSON.stringify(pager),
    })
    .then(resp => resp.json())
    .then(result => {
        console.log(result);
        
        const {list, pager} = result;

        if(list.length > 0) {
        	const books_empty = document.getElementById("books_empty"); 
            books_empty.classList.add("hide");

            document.querySelectorAll(".book-item").forEach(element => {
                element.remove();
            })
            
            list.forEach(addItem);
        }  
        
        updatePager(pager);
          
    }).catch(e => alert(e));
}

const updatePager = pager => {
	const next = document.querySelector(".pagination .page-item.next .page-link");
	const prev = document.querySelector(".pagination .page-item.prev .page-link");
	const last = document.querySelector(".pagination .page-item.last .page-link");

    next.dataset.page = pager.next;
    prev.dataset.page = pager.prev;
    last.dataset.page = pager.last;
    
    document.querySelectorAll(".page-item.page").forEach(item => {
        item.remove();
    })
	
	pager.list.forEach(page => {
		const pageItem = document.createElement("div");
		pageItem.classList.add("page-item");
		pageItem.classList.add("page");
		
		if(pager.page == page)
			pageItem.classList.add("active");
		
		const pageLink = document.createElement("div");
		pageLink.textContent = page;
		pageLink.classList.add("page-link");
        pageLink.addEventListener("click", e=> {
            pager.page = page;
            movePage(pager);
        })
		//a.href = `?page=${item.page}&${pager.search}=${pager.keyword}`;
		pageItem.append(pageLink);
		
		next.parentNode.before(pageItem);
	});
};

const deleteItem = e => {
	const {code} = e.target.dataset;
	
	fetch(`/book/${code}`,{
		method: "DELETE",
	}).then(resp => {
		if(resp.status == "200"){
			alert("삭제되었습니다.");
			const tr = document.querySelector(`tr[data-code='${code}']`);
			tr.remove();
		}
	}).catch(err => alert(err));
};

const updateItem = () => {
	const code = document.querySelector("#update_modal .code").textContent; 
	const title = document.querySelector("#update_modal input[name='title']").value;
	const publisher = document.querySelector("#update_modal input[name='publisher']").value;
	document.querySelector("#update_modal input[name='price']").value;
	document.querySelector("#update_modal input[name='pubDate']").value;
	
	const item = {code, title, publisher, price, pubDate};
	
	console.log(item);
	
	fetch("/book", {
		method: "PUT",
        body: JSON.stringify(item),
		headers: {
			"Content-Type": "application/json"
		}
	}).then(resp => resp.json())
	.then(result => {
		if(result){
			alert("변경되었습니다.");
			
			const tr = document.querySelector(`tr[data-code='${result.code}']`);

            tr.replaceChildren();
			makeItem(result, tr);
		}
	}).catch(err => alert(err));
}

const updateModal = e => {
    const { book_code, title, publisher, price, pubDate } = e.target.dataset;
    
    const code_tag = document.querySelector("#update_modal .code"); 
    code_tag.textContent = book_code;

    const title_tag = document.querySelector("#update_modal input[name='title']");
    title_tag.value = title;

    const publisher_tag = document.querySelector("#update_modal input[name='publisher']");
    publisher_tag.value = publisher;

    const price_tag = document.querySelector("#update_modal input[name='price']");
    price_tag.value = price;

    const pubDate_tag = document.querySelector("#update_modal input[name='pubDate']");
    pubDate_tag.value = pubDate;

    update_modal.toggle();

    //const body = document.querySelector("#update_modal .modal-body");
    //body.textContent = code;
};

const makeItem = (element, tr) => {
	const td_code = document.createElement("td");
    td_code.textContent = element.code;
    tr.append(td_code);

    const td_title = document.createElement("td");
    td_title.textContent = element.title;
    tr.append(td_title);

    const td_publisher = document.createElement("td");
    td_publisher.textContent = element.publisher;
    tr.append(td_publisher);

    const td_price = document.createElement("td");
    td_price.textContent = element.price;
    tr.append(td_price);

    const td_pubDate = document.createElement("td");
    td_pubDate.textContent = element.pubDate;
    tr.append(td_pubDate);

    const td_management = document.createElement("td");
    tr.append(td_management);
    const div_update = document.createElement("div");
    div_update.classList.add("btn", "btn-sm", "btn-warning");
    const i_update = document.createElement("i");
    i_update.classList.add("bi", "bi-tools");
    div_update.append(i_update);
    div_update.append(document.createTextNode(" 변경"));
    for(key in element)
        div_update.dataset[key] = element[key];
    div_update.dataset.code = element.code;
    div_update.addEventListener("click", updateModal);
    td_management.append(div_update);
        
    td_management.append(document.createTextNode(" "));

    const div_delete = document.createElement("div");
    div_delete.classList.add("btn", "btn-sm", "btn-danger");
    const i_delete = document.createElement("i");
    i_delete.classList.add("bi", "bi-trash");
    div_delete.append(i_delete);
    div_delete.append(document.createTextNode(" 삭제"));
    div_delete.dataset.code = element.code;
    td_management.append(div_delete);

    tr.append(td_management);
}

const addItem = element => {
	const tr = document.createElement("tr");
	tr.dataset.code = element.code;
    tr.classList.add("book-item");
	
	makeItem(element, tr);

    const books = document.getElementById("books");
    books.append(tr);
}

let update_modal;
window.addEventListener("load", () => {
    const add_modal = new bootstrap.Modal(document.getElementById("add_modal"));
    update_modal = new bootstrap.Modal(document.getElementById("update_modal"));

    document.getElementById("add").addEventListener("click", e => {
        add_modal.toggle();
    });
    
    document.querySelector("#update_modal .submit").addEventListener("click", e=> {
    	updateItem();
    
    	update_modal.toggle();
    });

    document.querySelector("#add_modal .submit").addEventListener("click", e => {
        const formData = new FormData();

        const title = document.querySelector("#add_modal input[name='title']").value;
        const publisher = document.querySelector("#add_modal input[name='publisher']").value;
        const price = document.querySelector("#add_modal input[name='price']").value;
        const pubDate = document.querySelector("#add_modal input[name='pubDate']").value;
        
        const uploadFile = [];
        document.querySelectorAll("#add_modal input[name='uploadFile']").forEach(element => {
        	uploadFile.push(element.files[0]);
        });
        
        formData.append("title", title);
        formData.append("publisher", publisher);
        formData.append("price", price);
        formData.append("pubDate", pubDate);
        formData.append("uploadFile", uploadFile);

        fetch("/book", {
            method: "POST",
            body: formData
        }).then(resp => resp.json())
        .then(result => {
            console.log(result);
            
            addItem(result);
        }).catch(err => alert(err));

        add_modal.toggle();
    });

    document.querySelectorAll(".pagination .page-link").forEach(element => {
        element.addEventListener("click", e => {
            const {page} = e.target.dataset;
            movePage({page});
        }); 
    });

    movePage({page:1});    
});