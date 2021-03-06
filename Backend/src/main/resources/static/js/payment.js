const URL = 'http://localhost:8080/api/payment';
const tableBody = document.querySelector('#tableBody');
const loader = document.querySelector('.spinner-border');
const createForm = document.querySelector('#add_payment');
const updateForm = document.querySelector('#update_payment_form');
const buttonClose = document.querySelectorAll('.btn-close');
let payments = [];

//handling submiting post the form
createForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const data = {
        member_id: createForm.parent_id.value,
        amount: createForm.amount.value,
        due: createForm.due.value,
        paid: createForm.paid.value,
        description: createForm.description.value
    }
    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            generateHtml(data);
            payments = data;
            createForm.reset();
            buttonClose[0].click();
        })
        .catch(error => console.log(error));
})

updateForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const data = {
        id: updateForm.id.value,
        member_id: updateForm.parent_id_update.value,
        amount: updateForm.amount_update.value,
        due: updateForm.due_update.value,
        paid: updateForm.paid_update.value,
        description: updateForm.description_update.value
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
            payments = data;
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
        const payment_id = e.target.getAttribute('data-id');
        const { id, member_id, amount, due, paid, description } = payments[payment_id]
        updateForm.parent_id_update.value = member_id;
        updateForm.amount_update.value = amount;
        updateForm.due_update.value = due;
        updateForm.paid_update.value = paid;
        updateForm.description_update.value = description;
        updateForm.id.value = id;
    }
})

const getAllPayments = () => {
    fetch(URL)
        .then(response => response.json())
        .then(data => {
            generateHtml(data);
            payments = data;
        })
        .catch(error => console.log(error));
}

getAllPayments();

const generateHtml = (data) => {
    let HTML = ``;
    data.forEach((payment, i) => {
        HTML += `
        <tr data-id="${payment.id}">

            <th scope="row">${i + 1}</th>
            <td>${payment.member_id}</td>
            <td>${payment.amount}</td>
            <td>${payment.due}</td>
            <td>${payment.paid}</td>
            <td>${payment.description}</td>
            <td><i class="fas fa-user-edit" data-id="${i}" data-bs-toggle="modal" data-bs-target="#editModal"></i><i class="fas fa-trash" data-id="${payment.id}"></i></td>
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



