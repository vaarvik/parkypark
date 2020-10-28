<template id="test-add-parkinglot">
    <div>
        <h2>Create a new parkinglot</h2>
        <form @submit="checkForm" :action=`"api/parkinglots/test"` method="post">
            <p>
                <label for="name">Name</label>
                <input type="text" name="name" id="name" v-model="name">
            </p>

            <p>
                <label for="address">Address</label>
                <input type="text" name="address" id="address" v-model="address">
            </p>

            <p>
                <label for="userid">Userid</label>
                <input type="text" name="userid" id="userid" v-model="userid">
            </p>

            <p>
                <label for="price">Price</label>
                <input type="number" step="1" name="price" id="price" v-model="price" min="0">
            </p>

            <p>
                <input type="submit" value="Create Parkinglot">
            </p>

        </form>
    </div>
</template>
<script>
    Vue.component("test-add-parkinglot", {
        template: "#test-add-parkinglot",
        data: () => ({
            name: null,
            address: null,
            //id: null,
            userid: null,
            price: null,
        }),
        methods:{
            checkForm:function(e) {
                const urlRegex = "/^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\\.(?:[a-z\u00a1-\uffff]{2,})))(?::\\d{2,5})?(?:[/?#]\\S*)?$/i";
                if(this.name && this.address && this.id /*&& this.id*/ && this.userid && this.price) return true;
                e.preventDefault();
            }
        }
    });
</script>