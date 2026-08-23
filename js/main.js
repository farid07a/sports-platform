console.log("Sport DZ java Script is working");

const clubs=[
    {
        id:1,
        name:"Biskra CITY",
        city:"Biskra",
        sport:"Football"
    },
    {
        id:2,
        name:"Biskra USB",
        city:"Biskra",
        sport:"Football"
    },
    {
        id:3,
        name:"JSD",
        city:"Djemorah",
        sport:"Football"
    }
]

const clubGrid = document.querySelector(".clubs-grid")

console.log(clubGrid);


clubs.forEach(item => {
    const card = document.createElement("article");
    card.classList.add("club-card");
    card.innerHTML = `
    <div class="club-card-content">

        <h2>${item.name}</h2>

        <p>City: ${item.city}</p>

        <p>Sport: ${item.sport}</p>

        <a href="club-details.html?id=${item.id}">View Club</a>

    </div> `;

    clubGrid.appendChild(card);
});


console.log("passe1");

const params = new URLSearchParams(window.location.search);
console.log("passe2");
const clubId = params.get("id");
console.log("passe3");
console.log(clubId);

console.log("passe4");


/*
clubs.forEach(item => {
    console.log(item.id);
    console.log(item.name);
    console.log(item.city);
    console.log(item.sport);
});


console.log("Name"+clubs[0].name);
console.log("City"+clubs[0].city);
console.log("Sport"+clubs[0].sport);
*/
//console.log(clubs);