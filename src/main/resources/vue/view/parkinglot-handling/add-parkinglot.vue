<template id="add-parkinglot">
    <div>
        <h2>Create a new parkinglot</h2>
        <form @submit="checkForm" :action=`/api/parkinglots/add` method="post">
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
    Vue.component("add-parkinglot", {
        template: "#add-parkinglot",
        data: () => ({
            name: null,
            address: null,
            userid: null,
            price: null,
        }),
        methods:{
            checkForm:function(e) {
            }
        }
    });
</script>
