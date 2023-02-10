const url = 'http://194.58.120.217/api/orders'

//функция заполнения главной таблицы ордеров
export function fillUsersTable() {
    const allUsersTableBody = document.getElementById('allOrderTableBody')

    $('#allOrderTableBody').empty()
    fetch(url, {
        Content: "application/json",
        method: "GET"
    })
        .then(response => response.json())
        .then(data => {
            let columnContent = ''
            data.forEach(element => {
                columnContent += `<tr>
                    <td>${element.date}</td>
                    <td>${element.status}</td>
                    <td>${element.orderContent}</td>
                    <td>${element.amount}</td>
                    <td>${element.buyer}</td>
                    <td>${element.phone}</td>
                    <td>${element.extra}</td>
                    <td>
                      <button type="button" class="btn btn-info" data-bs-toggle="modal"
                           data-index="${element.id}" data-bs-target="#modalEdit">Изменить
                       </button>
                     </td>
                     <td>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                          data-index="${element.id}"  data-bs-target="#modalDelete">Удалить
                        </button>
                     </td>
                </tr>
                `
            })
            allUsersTableBody.innerHTML = columnContent;
        })
}

//функция выбора статуса для нового ордера
export function getRolesForNewUser() {
    const selectStatusForOrder = document.getElementById('selectStatusForOrder')
    let resRoles = '';
    resRoles +=
        `
         <option value="1">Новый</option>
         <option value="2">Согласован</option>
         <option value="3">Ожидается доставка</option>
         <option value="4">На складе</option>
         <option value="5">Выполнен</option>
         <option value="6">Отменен</option>
        `
    selectStatusForOrder.innerHTML = resRoles
}

//функция создания нового ордера
export function createNewUser(e) {
    e.preventDefault()
    const newOrderForm = document.forms['formNewOrder']
    let currentOption = '';
    for (let option of document.getElementById('selectStatusForOrder').options) {
        if (option.selected) {
            currentOption = option.getAttribute('value')
        }
    }
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },body: JSON.stringify({
            amount: newOrderForm.amount.value,
            orderContent: newOrderForm.orderContent.value,
            buyer: newOrderForm.buyer.value,
            phone: newOrderForm.phone.value,
            extra: newOrderForm.extra.value,
            status: currentOption
        })
    }).then((response) => {
        if (response.ok) {
            newOrderForm.reset()
            fillUsersTable()
            $('.nav-tabs a[href="#OrderTable"]').tab('show')
        }
    })
}

//функция заполнения форм (редактирования, удаления)
export function fillUserForm(id, formName, method) {
    fetch(url + '/search?id=' + id)
        .then(response => response.json())
        .then(data => {
            formName.id.value = data.id
            formName.amount.value = data.amount
            formName.orderContent.value = data.orderContent
            formName.buyer.value = data.buyer
            formName.phone.value = data.phone
            formName.extra.value = data.extra
            let rolesForEditedUser = document.getElementById('status' + method)
            let resRoles = ''
            resRoles += `
            <option value="1">Новый</option>
            <option value="2">Согласован</option>
            <option value="3">Ожидается доставка</option>
            <option value="4">На складе</option>
            <option value="5">Выполнен</option>
            <option value="6">Отменен</option>
            `
            rolesForEditedUser.innerHTML = resRoles
        })
}

//функция редактирования ордера
export function updateCurrentUser(e) {
    e.preventDefault()
    let currentOption = '';
    for (let option of document.getElementById('statusEdit').options) {
        if (option.selected) {
            currentOption = option.getAttribute('value')
        }
    }
    let orderEditForm = document.forms['modalEditForm']
    fetch(url+ '/update?id=' + orderEditForm.id.value , {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json"
        },
        Content: "application/json",
        body: JSON.stringify({
            id: orderEditForm.id.value,
            amount: orderEditForm.amount.value,
            orderContent: orderEditForm.orderContent.value,
            buyer: orderEditForm.buyer.value,
            phone: orderEditForm.phone.value,
            extra: orderEditForm.extra.value,
            status: currentOption
        }),
    }).then((response) => {
        if (response.ok) {
            fillUsersTable()
            document.getElementById('closeEditModalWindow').click()
            $('.nav-tabs a[href="#UserTable"]').tab('show')
        }
    })
}

//функция удаления ордера
export function deleteCurrentUser(id) {
    fetch(url + '/' + id, {
        method: 'DELETE'
    }).then(() => {
        fillUsersTable()
        // document.getElementById('closeDeleteModal').click()
        $('.nav-tabs a[href="#UserTable"]').tab('show')
    })
}


export function findUserById() {
        const allUsersTableBody = document.getElementById('allOrderTableBody')
        const rows = allUsersTableBody.getElementsByTagName("tr");
        const searchInput = document.getElementById("searchInput").value;

        for (let i = 0; i < rows.length; i++) {
            const cells = rows[i].getElementsByTagName("td");
            for (let j = 0; j < cells.length; j++) {
                if (cells[j].innerHTML.toLowerCase().indexOf(searchInput.toLowerCase()) > -1) {
                    rows[i].style.display = "";
                    break;
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
}

