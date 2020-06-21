import 'dart:io';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class UserImagePicker extends StatefulWidget {
  final void Function(File pickedImage) onImagePicked;

  UserImagePicker(this.onImagePicked);

  @override
  _UserImagePickerState createState() => _UserImagePickerState();
}

class _UserImagePickerState extends State<UserImagePicker> {
  File _pickedImage;

  void _pickImage() async {
    final imagePicker = ImagePicker();
    final imagePFile = await imagePicker.getImage(
      source: ImageSource.camera,
      imageQuality: 50,
      maxWidth: 150,
    );
    if (imagePFile != null) {
      final imageFile = File(imagePFile.path);
      setState(() {
        _pickedImage = imageFile;
      });
      widget.onImagePicked(_pickedImage);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        CircleAvatar(
          backgroundColor: Colors.grey,
          backgroundImage:
              _pickedImage != null ? FileImage(_pickedImage) : null,
          radius: 45,
        ),
        FlatButton.icon(
          onPressed: _pickImage,
          icon: const Icon(Icons.image),
          label: const Text('Add Image'),
          textColor: Theme.of(context).primaryColor,
        ),
      ],
    );
  }
}
