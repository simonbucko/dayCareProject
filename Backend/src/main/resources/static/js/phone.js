const URL = 'http://localhost:8080/api/phonelist';
const tableBody = document.querySelector('#tableBody');
const loader = document.querySelector('.spinner-border');
const createForm = document.querySelector('#add_phone_form');
const updateForm = document.querySelector('#update_phone_form');
const buttonClose = document.querySelectorAll('.btn-close');
let phoneDetails = [];
const d = new Date()
const today = d.toISOString().slice(0, 10)
//handling submiting post the form
createForm.registerDate.value = today
createForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const data = {
        parentFirstName: createForm.parentFirstName.value,
        parentLastName:createForm.parentLastName.value,
        registerDate:createForm.registerDate.value,
        phoneNumber: createForm.phoneNumber.value,

    }
    console.log(data)
    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data => {
            generateHtml(data);
            phoneDetails = data;
            createForm.reset();
            buttonClose[0].click();
        })
        .catch(error => console.log(error));
})

updateForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const data = {
        id: updateForm.id.value,
        parentFirstName: updateForm.parentFirstName.value,
        parentLastName: updateForm.parentLastName.value,
        phoneNumber: updateForm.phoneNumber.value,
        registerDate:updateForm.registerDate.value,

    }
    fetch(URL, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data => {
            generateHtml(data);
            phoneDetails = data;
            updateForm.reset();
            buttonClose[1].click();
        })
        .catch(error => console.log(error));
})

//deleting row from table
tableBody.addEventListener('click', (e) => {
    //deleting
    if (e.target.classList.contains('fa-trash')) {
        const id = e.target.getAttribute('data-id');
        document.querySelector(`tr[data-id="${id}"]`).remove();
        fetch(`${URL}/${id}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                generateHtml(data);
            })
            .catch(error => console.log(error));
    }
    //editing
    if (e.target.classList.contains('fa-user-edit')) {
        const phone_id = e.target.getAttribute('data-id');


        const { id, parentFirstName,parentLastName, phoneNumber } = phoneDetails[phone_id]
        updateForm.parentFirstName.value = parentFirstName;
        updateForm.parentLastName.value = parentLastName;
        updateForm.phoneNumber.value = phoneNumber;
        updateForm.id.value = id;
        updateForm.registerDate.value = today;
        // console.log(phoneDetails[phone_id].registerDate)

    }
})

const getALlPhoneDetails = () => {
    fetch(URL)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            generateHtml(data);
            phoneDetails = data;
        })
        .catch(error => console.log(error));
}

getALlPhoneDetails();

const generateHtml = (data) => {
    let HTML = ``;
    data.forEach((phoneList, i) => {
        HTML += `
        <tr data-id="${phoneList.id}">
            <th scope="row">${i + 1}</th>
            <td>${phoneList.parentFirstName}</td>
            <td>${phoneList.parentLastName}</td>
            <td>${phoneList.registerDate}</td>
            <td>${phoneList.phoneNumber}</td>
            <td><i class="fas fa-user-edit" data-id="${i}" data-bs-toggle="modal" data-bs-target="#editModal"></i><i class="fas fa-trash" data-id="${phoneList.id}"></i></td>
        </tr>
        `
    });
    HTML += `
        <tr class="add_child">
            <td colspan="7"><i class="fas fa-plus-circle" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-toggle="tooltip" data-bs-placement="top" title="Add new record"></i></td>
        </tr>
    `
    loader.style.display = 'none';
    tableBody.innerHTML = HTML;
}