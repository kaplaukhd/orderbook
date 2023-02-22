const base_url = 'http://localhost:8189/api';

window.onload = () => {
    getBaseInfo()
    getWbProducts()
}


function getBaseInfo() {
    const totalSales = document.getElementById('total_sales');
    const revenue = document.getElementById('revenue_month');
    const customers = document.getElementById('total_customers');

    fetch('http://localhost:8189/api/orders/info', {
        Content: "application/json",
        method: "GET"
    })
        .then(response => response.json())
        .then(data => {
            totalSales.innerHTML = data.all_purschaces;
            revenue.innerHTML = data.summ + '₽';
            customers.innerHTML = data.all;
        });
}

function getLineData() {
//blue - sales
//orange - customers
//green - revenue

    fetch(url + '/orders', {
        Content: "application/json",
        method: "GET"
    })
        .then(response => response.json())
        .then(data => {
            const d3 = require('d3');
            const chartData = d3.nest().key(function (d) {
                return d.status;
            }).rollup(function (v) {
                return d3.sum(v, function (d) {
                    return d.amount;
                });
            }).entries(data);

            new ApexCharts(document.querySelector("#reportsChart"), {series: chartData}).render();
        })

}

function getWbProducts() {
    const tableBody = document.getElementById('wb-table-body');
    fetch('http://77.232.37.111:7054/v1/api/products', {
        Content: "application/json",
        method: "GET"
    })
        .then(response => response.json())
        .then(data => {
            let columnContent = '';
            for (let i = 0; i < 6; i++) {
                columnContent += `<tr>
                    <td>${data[i].nmid}</td>
                    <td>${data[i].name}</td>
                    <td>${data[i].salePrice}</td>
                    </tr>
                    `
            }
            tableBody.innerHTML = columnContent;
        })
}

