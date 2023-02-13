const url = 'http://localhost:8189/api/orders'

window.onload = () => {
    fillUsersTable()
}



//функция заполнения главной таблицы ордеров
function fillUsersTable() {
    const allUsersTableBody = document.getElementById('allOrderTableBody')

    // $('#allOrderTableBody').empty()
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