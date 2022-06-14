var link_a = document.links;

for (var item of link_a) {
    if (!item.href.startsWith("https://fullstack.edu.vn")) {
        item.onclick = function(e) {
            e.preventDefault();
        }
    }
}