<script setup>
// 传递过来的值是readOnly的
// const props = defineProps(["money", "books"]);

const props = defineProps({
  money: {
    type: Number,
    required: true,
    default: 200
  },
  books: {
    type: Array,
  }
})

// 使用defineEmits定义事件
const emits = defineEmits(['buy']);

function buyBooks() {
  // props.money -= 10;
  // 点击触发buyBooks函数，emits触发事件，并传值
  emits('buy', -10)
}
</script>

<template>
  <div style="color :white; background-color: pink">
    <h2>
      <!--   这是插槽    -->
      <slot name="title">
        haha
      </slot>
    </h2>
    <h2>Son</h2>
    <h3>{{ props.money }}</h3>
    <h3>{{ props.books }}</h3>
    <button @click="buyBooks">
      <slot name="btn">购买黄色小说</slot>
    </button>
  </div>
</template>

<style scoped>

</style>