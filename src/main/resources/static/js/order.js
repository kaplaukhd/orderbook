// import {
//     fillUsersTable,
//     getRolesForNewUser, createNewUser,
//     fillUserForm, updateCurrentUser, deleteCurrentUser,findUserById
// } from "./functions.js";
//
//
// window.onload = () => {
//     fillUsersTable()
// }
//
// $(document).ready(() => {
//     document.getElementById("searchInput").addEventListener(
//         "input", findUserById
//     )
//     //при активации вкладки Нового юзера зполняются роли
//     $('.nav-tabs a[href="#NewOrder"]').on('show.bs.tab', () => {
//         getRolesForNewUser()
//         //при нажатии кноки создания нового юзера создаётся юзер
//         document.getElementById('newOrderButton').addEventListener('click', createNewUser)
//     })
//
//     //очистка формы нового юзера
//     $('.nav-tabs a[href="#OrderTable"]').on('show.bs.tab', () => {
//         document.getElementById('formNewOrder').reset()
//     })
//
//     //заполнение формы редактирования юзера
//     $('#modalEdit').off().on('show.bs.modal', event => {
//         let id = $(event.relatedTarget).attr("data-index")
//         fillUserForm(id, document.forms['modalEditForm'], 'Edit')
//         document.getElementById('editOrder').addEventListener('click', updateCurrentUser)
//
//     })
//
//     $('#modalDelete').on('show.bs.modal', event => {
//         let id = $(event.relatedTarget).attr("data-index")
//         fillUserForm(id, document.forms['modalDeleteForm'], 'Delete')
//         document.getElementById('closeDeleteModal').addEventListener('click', (event) => deleteCurrentUser(id))
//     })
//
// })