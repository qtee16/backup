

export function getCard(ID) {
    const cards = [
        {
            id: 1,
            name: "card1",
            atk: 100,
            def: 80
        },
        {
            id: 2,
            name: "card2",
            atk: 120,
            def: 90
        },
        {
            id: 3,
            name: "card3",
            atk: 100,
            def: 140
        },
        {
            id: 4,
            name: "card4",
            atk: 90,
            def: 100
        },
        {
            id: 5,
            name: "card5",
            atk: 100,
            def: 180
        },
        {
            id: 6,
            name: "card6",
            atk: 70,
            def: 80
        },
        {
            id: 7,
            name: "card7",
            atk: 110,
            def: 100
        },
        {
            id: 8,
            name: "card8",
            atk: 100,
            def: 40
        },
        {
            id: 9,
            name: "card9",
            atk: 200,
            def: 80
        },
        {
            id: 10,
            name: "card10",
            atk: 190,
            def: 150
        },
        {
            id: 11,
            name: "card11",
            atk: 140,
            def: 200
        },
        {
            id: 12,
            name: "card12",
            atk: 190,
            def: 80
        },
        {
            id: 13,
            name: "card13",
            atk: 200,
            def: 180
        },
        {
            id: 14,
            name: "card14",
            atk: 170,
            def: 130
        },
        {
            id: 15,
            name: "card15",
            atk: 250,
            def: 80
        },
        {
            id: 16,
            name: "card16",
            atk: 160,
            def: 110
        },
        {
            id: 17,
            name: "card17",
            atk: 190,
            def: 50
        },
        {
            id: 18,
            name: "card18",
            atk: 250,
            def: 40
        },
        {
            id: 19,
            name: "card19",
            atk: 150,
            def: 180
        },
        {
            id: 20,
            name: "card20",
            atk: 240,
            def: 70
        }
    ];
    return cards[ID - 1];
}