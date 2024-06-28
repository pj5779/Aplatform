<template>
  <section class="call-to-action with-full-borders mb-2">
    <div class="col-lg-12">
      <div class="form-group col">
        <div class="col-lg-5 form-check-inline">
          <input
            type="text"
            maxlength="50"
            class="form-control text-3 h-auto py-2"
            name="title"
            placeholder="제목"
            :value="title"
            @input="$emit(update:title, $event.target.value)"
            required
          />
        </div>
        <div class="col-lg-1 form-check-inline">
          <a class="btn btn-primary btn-circle mb-2 imgIcon" @click="$emit('removeComponents', props.id)">
            <i class="fa fa-minus"></i>
          </a>
          {{ props.id }}
        </div>
      </div>
    </div>
    <div class="col-lg-12">
      <div class="form-group col">
        <textarea
          maxlength="5000"
          rows="8"
          class="form-control text-3 h-auto py-2"
          name="content"
          placeholder="내용"
          :value="content"
          @input="$emit(update:content, $event.target.value)"
          required
        ></textarea>
      </div>
    </div>

    {{ props.id }}
  </section>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps("id");

defineProps({
  title: String,
  content: String,
});

defineEmits(["update:title", "update:content"]);
</script>

<style scoped></style>
