import UploadAdapter from "/js/ImageUploadAdapter.js";

ClassicEditor.create(document.querySelector('#editor'), {
    language: 'ko',
    extraPlugins: [MyCustomUploadAdapterPlugin],
    removePlugins: ['Base64UploadAdapter', 'Heading',],
    fontSize: {
        options: [
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
        ],
    }
})
    .then(newEditor => {
        window.editorInstance = newEditor;
        if (product != "" || product != null) {
            editorInstance.setData(product);
        }

    })
    .catch(error => {
        console.error(error);
    });

// const editor1 = $('.ck-editor__editable_inline').ckeditorInstance.editor;
function MyCustomUploadAdapterPlugin(editor) {
    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
        return new UploadAdapter(loader)
    }
}
