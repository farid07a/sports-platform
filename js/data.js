
const clubs=[
    {
        id:1,
        name:"Biskra CITY",
        city:"Biskra",
        sport:"Football",
        image: "images/cl1.jpg"

    },
    {
        id:2,
        name:"Batna USB",
        city:"Batna",
        sport:"Tennis",
        image: "images/cl2.jpg"
    },
    {
        id:3,
        name:"JSD",
        city:"Djemorah",
        sport:"KickBoxing",
        image: "images/cl3.jpg"
    }
]

const players = [
    {
        id: 1,
        clubId: 1,
        firstName: "Ahmed",
        lastName: "Ali",
        position: "Forward",
        birthDate: "2002-05-10",
        image: "images/players/player-1.jpeg"
    },

    {
        id: 2,
        clubId: 1,
        firstName: "Mohamed",
        lastName: "Salah",
        position: "Midfielder",
        birthDate: "2001-08-20",
        image: "images/players/player-2.jpg"
    },

    {
        id: 3,
        clubId: 2,
        firstName: "Karim",
        lastName: "Ben",
        position: "Defender",
        birthDate: "2003-01-15",
        image: "images/players/player-3.jpg"
    }
];

const matches = [
    {
        id: 1,
        clubId: 1,
        opponent: "Biskra United",
        date: "2026-09-05",
        location: "Biskra Stadium",
        result: "Upcoming"
    },
    {
        id: 2,
        clubId: 1,
        opponent: "El Oued FC",
        date: "2026-09-10",
        location: "El Oued Stadium",
        result: "Upcoming"
    },
    {
        id: 3,
        clubId: 2,
        opponent: "Biskra FC",
        date: "2026-09-08",
        location: "Biskra Stadium",
        result: "Upcoming"
    }
];