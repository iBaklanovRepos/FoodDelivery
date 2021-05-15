const tabItems = document.querySelectorAll(".tab-item");
const tabContentItems = document.querySelectorAll(".tab-content-item");

function selectItem(e) {
    removeBorder();
    this.classList.add("tab-border");
    removeShow();
    const tabContentItem = document.querySelector(`#${this.id}-content`);
    tabContentItem.classList.add("show");
    prevTable = curTable;
    curTable = tabContentItem.classList[1];
    addBtn.innerText = addBtn.innerText.replace(prevTable, curTable);
}

const modal = document.getElementById("modal-window");
const editBtns = document.querySelectorAll(".btn-edit");
const addBtn = document.getElementById("btn-add");
const activateModalBtns = document.querySelectorAll(".modal-activate");
const closeModalBtn = document.getElementById("close-modal");
const declineBtn = document.getElementById("modal-decline");
const deleteButtons = document.querySelectorAll(".btn-dlt");

let curTable = "restaurant";
let prevTable = "";
let pressedButton;
let selectedRow;
let req;
let isIE;

addBtn.addEventListener("click", openAddModal);
editBtns.forEach((item) => item.addEventListener("click", openEditModal));
deleteButtons.forEach((item) => item.addEventListener("click", deleteRow));

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') !== -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}


function parseMessage() {

}

function callback() {
    if (req.readyState === 4) {
        if (req.status === 200) {
            parseMessage(req.responseXML);
        }
    }
}

function deleteRow(e) {
    // console.log(this.parentNode);
    const row = this.parentNode.parentNode;

    const id = row.firstElementChild.textContent;
    const showElement = document.getElementsByClassName("show");
    const table = showElement.item(0).classList.item(1);
    const orderTable = document.getElementById("order-table");
    const name = row.cells[1].textContent;
    console.log(orderTable.rows.length);
    switch (curTable) {
        case "order":
            break;
        case "restaurant":
            for (let i = 0; i < orderTable.rows.length; i++) {
                if (orderTable.rows[i].cells[2].innerText === name) {
                    orderTable.removeChild(orderTable.rows[i]);
                }
            }
            // Array.prototype.forEach.call(orderTable.children, child => {
            //     if (child.cells[2].innerText === name) {
            //         child.classList.add("to-be-deleted");
            //     }
            // });
            // const deletedRows = document.getElementsByClassName("to-be-deleted");
            // console.log(deletedRows);
            // Array.prototype.forEach.call(deletedRows, child => {
            //     child.parentNode.removeChild(child);
            // });
            // Array.prototype.forEach.call(deletedRows, row => {
            //
            //
            // });

            break;
        case "customer":
            for (let i = 0; i < orderTable.rows.length; i++) {
                if (orderTable.rows[i].cells[1].innerText === name) {
                    orderTable.removeChild(orderTable.rows[i]);
                }
            }
            break;
        case "courier":
            for (let i = 0; i < orderTable.rows.length; i++) {
                if (orderTable.rows[i].cells[3].innerText === name) {
                    orderTable.removeChild(orderTable.rows[i]);
                }
            }
            break;
        case "status":
            for (let i = 0; i < orderTable.rows.length; i++) {
                if (orderTable.rows[i].cells[4].innerText === name) {
                    orderTable.removeChild(orderTable.rows[i]);
                }
            }
            break;
        default:
            console.log(err);
            break;
    }
    let url = "delete?table=" + table + "&id=" + id;
    // console.log(url);
    // console.log(showElement);
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);

    row.parentNode.removeChild(row);


}

function openEditModal() {
    pressedButton = "edit";
    openModal(this);
}

function openAddModal() {
    pressedButton = "add";
    openModal(this);
}

function openModal(e) {


    if (pressedButton === "edit") {
        document.querySelector(".modal-header h2").textContent = "Edit " + curTable;
        selectedRow = e.parentNode.parentNode;
        const inputs = document.querySelectorAll("." + curTable + "-modal-input");
        // console.log(inputs);
        console.log(selectedRow);

        for (let i = 0; i < inputs.length; i++) {
            inputs[i].value = selectedRow.cells[i + 1].innerText;
        }


        // console.log("edit");
    } else if (pressedButton === "add") {


        document.querySelector(".modal-header h2").textContent = "Add " + curTable;

    }

    const prevShownModal = document.querySelector(".show-modal");
    prevShownModal.classList.remove("show-modal");

    let shownModal;

    shownModal = document.querySelector('#' + curTable + '-modal-body');
    shownModal.classList.add("show-modal");
    modal.style.display = "block";
}

document.getElementById("modal-accept").addEventListener("click", function () {
    let url = pressedButton + "?table=" + curTable;
    if (pressedButton === "edit") {
        const id = selectedRow.firstElementChild.textContent;
        url += "&id=" + id;
    }
    const inputs = document.querySelectorAll("." + curTable + "-modal-input");
    if (checkInputsValidity(inputs)) {
        console.log("inputs are valid");
        switch (curTable) {
            case "order":
                url += "&customer=" + inputs[0].value + "&restaurant=" + inputs[1].value + "&courier=" + inputs[2].value + "&status=" + inputs[3].value + "&details=" + inputs[4].value;
                break;
            case "restaurant":
                url += "&name=" + inputs[0].value + "&address=" + inputs[1].value + "&description=" + inputs[2].value;
                break;
            case "customer":
                url += "&name=" + inputs[0].value + "&address=" + inputs[1].value;
                break;
            case "courier":
                url += "&name=" + inputs[0].value + "&location=" + inputs[1].value + "&description=" + inputs[2].value;
                break;
            case "status":
                url += "&name=" + inputs[0].value + "&description=" + inputs[1].value;
                break;
            default:
                console.log(err);
                break;
        }
        // req = initRequest();
        // req.open("POST", url, true);
        // req.onreadystatechange = callback;
        // req.send();
        let doc;
        fetch(url).then(function (response) {
            response.text().then(function (text) {
                console.log(text);
                doc = parseXML(text);
                if (pressedButton === "add") {
                    addTableRow(doc.documentElement);
                } else if (pressedButton === "edit") {
                    editTableRow(doc.documentElement);
                }

            });
        });
        closeModal();
    } else {
        console.log("inputs are invalid")
    }


});

function checkInputsValidity(inputs) {
    for (let i = 0; i < inputs.length; i++) {
        if (!inputs[i].checkValidity()) {
            return false;
        }
    }
    return true;
}

function editTableRow(data) {
    console.log(selectedRow);
    const orderTable = document.getElementById("order-table");

    switch (data.nodeName) {
        case "order":

            selectedRow.cells[0].innerText = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            selectedRow.cells[1].innerText = data.getElementsByTagName("customer")[0].childNodes[0].nodeValue;
            selectedRow.cells[2].innerText = data.getElementsByTagName("restaurant")[0].childNodes[0].nodeValue;
            selectedRow.cells[3].innerText = data.getElementsByTagName("courier")[0].childNodes[0].nodeValue;
            selectedRow.cells[4].innerText = data.getElementsByTagName("status")[0].childNodes[0].nodeValue;
            if (typeof data.getElementsByTagName("details")[0].childNodes[0] != "undefined") {
                selectedRow.cells[5].innerText = data.getElementsByTagName("details")[0].childNodes[0].nodeValue;
            } else {
                selectedRow.cells[5].innerText = "";
            }

            break;
        case "restaurant":
            for (let i = 0; i < orderTable.rows.length; i++) {
                if (orderTable.rows[i].cells[2].innerText === selectedRow.cells[1].innerText) {
                    orderTable.rows[i].cells[2].innerText = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                }
            }
            selectedRow.cells[0].innerText = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            selectedRow.cells[1].innerText = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
            selectedRow.cells[2].innerText = data.getElementsByTagName("address")[0].childNodes[0].nodeValue;
            if (typeof data.getElementsByTagName("description")[0].childNodes[0] != "undefined") {
                selectedRow.cells[3].innerText = data.getElementsByTagName("description")[0].childNodes[0].nodeValue;
            } else {
                selectedRow.cells[3].innerText = "";
            }


            break;
        case "customer":
            for (let i = 0; i < orderTable.rows.length; i++) {
                if (orderTable.rows[i].cells[1].innerText === selectedRow.cells[1].innerText) {
                    orderTable.rows[i].cells[1].innerText = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                }
            }
            selectedRow.cells[0].innerText = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            selectedRow.cells[1].innerText = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
            selectedRow.cells[2].innerText = data.getElementsByTagName("address")[0].childNodes[0].nodeValue;
            break;
        case "courier":
            for (let i = 0; i < orderTable.rows.length; i++) {
                if (orderTable.rows[i].cells[3].innerText === selectedRow.cells[1].innerText) {
                    orderTable.rows[i].cells[3].innerText = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                }
            }
            selectedRow.cells[0].innerText = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            selectedRow.cells[1].innerText = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
            selectedRow.cells[2].innerText = data.getElementsByTagName("location")[0].childNodes[0].nodeValue;
            if (typeof data.getElementsByTagName("description")[0].childNodes[0] != "undefined") {
                selectedRow.cells[3].innerText = data.getElementsByTagName("description")[0].childNodes[0].nodeValue;
            } else {
                selectedRow.cells[3].innerText = "";
            }
            break;
        case "status":
            for (let i = 0; i < orderTable.rows.length; i++) {
                if (orderTable.rows[i].cells[4].innerText === selectedRow.cells[1].innerText) {
                    orderTable.rows[i].cells[4].innerText = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                }
            }
            selectedRow.cells[0].innerText = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            selectedRow.cells[1].innerText = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
            if (typeof data.getElementsByTagName("description")[0].childNodes[0] != "undefined") {
                selectedRow.cells[2].innerText = data.getElementsByTagName("description")[0].childNodes[0].nodeValue;
            } else {
                selectedRow.cells[2].innerText = "";
            }
            break;
        default:
            console.log(err);
            break;
    }
}

function addTableRow(data) {
    const targetTable = document.getElementById(data.nodeName + "-table");

    const newRow = targetTable.insertRow();
    let newCell;
    switch (data.nodeName) {
        case "order":
            newRow.insertCell().innerHTML = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("customer")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("restaurant")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("courier")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("status")[0].childNodes[0].nodeValue;
            if (typeof data.getElementsByTagName("details")[0].childNodes[0] != "undefined") {
                newRow.insertCell().innerHTML = data.getElementsByTagName("details")[0].childNodes[0].nodeValue;
            } else {
                newRow.insertCell().innerHTML = "";
            }

            break;
        case "restaurant":
            newRow.insertCell().innerHTML = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("address")[0].childNodes[0].nodeValue;
            if (typeof data.getElementsByTagName("description")[0].childNodes[0] != "undefined") {
                newRow.insertCell().innerHTML = data.getElementsByTagName("description")[0].childNodes[0].nodeValue;
            } else {
                newRow.insertCell().innerHTML = "";
            }

            break;
        case "customer":
            newRow.insertCell().innerHTML = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("address")[0].childNodes[0].nodeValue;
            break;
        case "courier":
            newRow.insertCell().innerHTML = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("location")[0].childNodes[0].nodeValue;
            if (typeof data.getElementsByTagName("description")[0].childNodes[0] != "undefined") {
                newRow.insertCell().innerHTML = data.getElementsByTagName("description")[0].childNodes[0].nodeValue;
            } else {
                newRow.insertCell().innerHTML = "";
            }
            break;
        case "status":
            newRow.insertCell().innerHTML = data.getElementsByTagName("id")[0].childNodes[0].nodeValue;
            newRow.insertCell().innerHTML = data.getElementsByTagName("name")[0].childNodes[0].nodeValue;
            if (typeof data.getElementsByTagName("description")[0].childNodes[0] != "undefined") {
                newRow.insertCell().innerHTML = data.getElementsByTagName("description")[0].childNodes[0].nodeValue;
            } else {
                newRow.insertCell().innerHTML = "";
            }
            break;
        default:
            console.log(err);
            break;
    }

    const editBtn = document.createElement("button");
    editBtn.classList.add("btn");
    editBtn.classList.add("btn-edit");
    editBtn.classList.add("modal-activate");
    editBtn.addEventListener("click", openEditModal);
    editBtn.innerHTML = `
                  <a href="edit?table=` + data.nodeName + `&id="` + data.getElementsByTagName("id")[0].childNodes[0].nodeValue + `></a>
                  <i class="fas fa-edit"></i>`;
    newCell = newRow.insertCell();
    newCell.appendChild(editBtn);
    const dltBtn = document.createElement("button");
    dltBtn.classList.add("btn");
    dltBtn.classList.add("btn-dlt");
    dltBtn.innerHTML = `<i class="fas fa-trash-alt"></i>`;
    dltBtn.addEventListener("click", deleteRow);
    newCell = newRow.insertCell();
    newCell.appendChild(dltBtn);
    targetTable.appendChild(newRow);

}


function parseXML(xmlString) {
    let doc;
    if (window.ActiveXObject) {
        // Internet Explorer
        doc = new ActiveXObject("MSXML.DOMDocument");
        doc.async = false;
        doc.loadXML(xmlString);
    } else {
        // Other browsers
        const parser = new DOMParser();
        doc = parser.parseFromString(xmlString, "text/xml");
    }
    return doc;
}

function clearInputFields() {
    document.querySelectorAll(".input-field input").forEach((item) => item.value = "");
}

function closeModal(e) {
    modal.style.display = "none";
    clearInputFields();
}

// declineBtn.addEventListener("click", closeModal);

closeModalBtn.addEventListener("click", closeModal);

// activateModalBtns.forEach((item) => item.addEventListener("click", openModal));


function removeBorder() {
    tabItems.forEach((item) => item.classList.remove("tab-border"));
}

function removeShow() {
    tabContentItems.forEach((item) => item.classList.remove("show"));
}

tabItems.forEach((item) => item.addEventListener("click", selectItem));
