// server.mjs
import { createServer } from 'node:http';

let list = [];
let seq = 0;

for(let i=0; i < 10; i++){
    list.push({
        bookid:++seq,
        bookname: `도서명 ${seq}`,
        publisher: `출판사 ${seq}`,
        price: 1000 * seq
    });
}

const server = createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'application/json' });

  const body = [];
  req.on("readable", () => {
    let chunk;
    while(null !== (chunk = req.read()))
      body.push(chunk);
  });

  req.on("end", () => {
    if(req.method === "POST"){
      if(body) {
        const item = JSON.parse(body);
        
        if(item){
          item.bookid = ++seq;
          list.push(item);
        }
      }
    } else if(req.method === "DELETE"){
      const bookid = req.url.split("/").at(-1);

      if(bookid){
        list = list.filter(value => value.bookid != bookid);
      }
    } else if(req.method === "PUT"){

    }

    res.end(JSON.stringify(list));
  })
});

// starts a simple http server locally on port 3000
server.listen(3000, '0.0.0.0', () => {
  console.log('Listening on localhost:3000');
});

// run with `node server.mjs`
