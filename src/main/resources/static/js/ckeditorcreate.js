import UploadAdapter from "/js/ImageUploadAdapter.js";

let editorInstance;
if (editorInstance) {
    editorInstance.destroy()
        .then(() => {
            console.log('에디터가 성공적으로 파괴되었습니다.');
            editorInstance = null; // 에디터 인스턴스 변수를 초기화
        })
        .catch(error => {
            console.error('에디터 파괴 중 오류가 발생했습니다.', error);
        });
}
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
        editorInstance = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

function MyCustomUploadAdapterPlugin(editor) {
    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
        return new UploadAdapter(loader)
    }
}
